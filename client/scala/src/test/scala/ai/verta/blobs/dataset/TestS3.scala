package ai.verta.blobs.dataset

import ai.verta.client._
import ai.verta.blobs._
import ai.verta.swagger.client.HttpException
import ai.verta.swagger._public.modeldb.versioning.model.VersioningSetTagRequestResponse

import scala.concurrent.ExecutionContext
import scala.language.reflectiveCalls
import scala.util.{Try, Success, Failure}

import org.scalatest.FunSuite
import org.scalatest.Assertions._

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3._
import com.amazonaws.services.s3.model._
import collection.JavaConverters._

import scala.collection.mutable.HashSet

class TestS3 extends FunSuite {
  /** Verify that a FileMetadata has correct path and does not have weird parameter
   *  @param metadata file's metadata
   *  @param path file's path (to be checked)
   */
  def assertMetadata(metadata: FileMetadata, path: String) = {
    assert(metadata.path.equals(path))
    assert(metadata.size > 0)
    assert(metadata.lastModified > 0)
    assert(metadata.md5.length > 0)
  }

  def fixture =
    new {
      val testfilePath = "s3://verta-scala-test/testdir/testfile"
      val testfileLoc = S3Location(testfilePath).get

      val testfilePath2 = "s3://verta-scala-test/testdir/testsubdir/testfile2"
      val testfileLoc2 = S3Location(testfilePath2).get

      val testdirPath = "s3://verta-scala-test/testdir/"
      val testdirLoc = S3Location(testdirPath).get

      val testsubdirPath = "s3://verta-scala-test/testdir/testsubdir/"
      val testsubdirLoc = S3Location(testsubdirPath).get

      val bucketLoc = S3Location("s3://verta-scala-test").get
    }

  test("S3Location should correctly determine bucket name and key") {
    val f = fixture
    assert(f.testfileLoc.bucketName.equals("verta-scala-test"))
    assert(f.testfileLoc.key.get.equals("testdir/testfile"))

    assert(f.bucketLoc.bucketName.equals("verta-scala-test"))
    assert(f.bucketLoc.key.isEmpty)
  }

  test("S3Location construction should fail when encountered non-S3 path") {
    val s3LocAttempt = S3Location("http://verta-starter/census-train.csv")
    assert(s3LocAttempt.isFailure)
    assert(s3LocAttempt match {case Failure(e) => e.getMessage contains "Illegal path; must be an S3 location"})
  }

  test("S3 blob should retrieve the file (i.e with key) correctly") {
    val f = fixture
    val s3Blob = S3(List(f.testfileLoc)).get

    val s3File = s3Blob.getMetadata(f.testfilePath).get
    assertMetadata(s3Blob.getMetadata(f.testfilePath).get, f.testfilePath)
  }

  test("S3 blob should retrieve the entire bucket correctly") {
    val f = fixture
    val s3Blob = S3(List(f.bucketLoc)).get

    assertMetadata(s3Blob.getMetadata(f.testfilePath).get, f.testfilePath)
    assertMetadata(s3Blob.getMetadata(f.testfilePath2).get, f.testfilePath2)
  }

  // testdir/testfile has two versions
  test("S3 should retrieve the correct version") {
    val f = fixture
    val s3: AmazonS3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build()
    var versionListing = s3.listVersions("verta-scala-test", "testdir/testfile")

    // get latest version
    val newVersionId = versionListing.getVersionSummaries().asScala.toList
    .filter((version: S3VersionSummary) => version.getKey().charAt(version.getKey().length() - 1) != '/') // not a folder
    .filter(_.isLatest()).head.getVersionId()

    // default should be the latest version
    val s3BlobDefault = S3(List(f.testfileLoc)).get
    var s3BlobLatest = S3(List(S3Location(f.testfilePath, Some(newVersionId)).get)).get

    assert(s3BlobDefault equals s3BlobLatest)
    assert(s3BlobLatest.getVersionId(f.testfilePath).get equals newVersionId)

    // get older version
    val oldVersionId = versionListing.getVersionSummaries().asScala.toList
    .filter((version: S3VersionSummary) => version.getKey().charAt(version.getKey().length() - 1) != '/') // not a folder
    .filter(!_.isLatest()).head.getVersionId()
    val s3BlobOld = S3(List(S3Location(f.testfilePath, Some(oldVersionId)).get)).get

    assert(!s3BlobDefault.equals(s3BlobOld))
    assert(s3BlobOld.getVersionId(f.testfilePath).get equals oldVersionId)
  }

  test("S3 should retrieve multiple keys correctly") {
    val f = fixture
    val s3Blob = S3(List(f.testfileLoc, f.testfileLoc2)).get

    assertMetadata(s3Blob.getMetadata(f.testfilePath).get, f.testfilePath)
    assertMetadata(s3Blob.getMetadata(f.testfilePath2).get, f.testfilePath2)
  }

  test("S3 should not have duplicate paths") {
    val f = fixture
    val s3Blob1 = S3(List(f.testfileLoc, f.testfileLoc, f.testdirLoc, f.testsubdirLoc, f.testfileLoc2)).get

    val s3Blob2 = S3(List(f.testfileLoc, f.testfileLoc2)).get
    assert(s3Blob1 equals s3Blob2)
  }

  test("S3 blob construction should fail when an invalid path is passed") {
    val f = fixture
    val invalidPath = "s3://verta-scala-test/testdir/no-such-file"
    val s3LocInvalid = S3Location(invalidPath).get

    val s3Blob = S3(List(s3LocInvalid, f.testfileLoc))

    assert(s3Blob.isFailure)
    assert(s3Blob match {case Failure(e) => e.getMessage contains "Not Found"})
  }

  test("S3 should produce similar hash and file size to PathBlob") {
    val f = fixture
    val s3Blob = S3(List(f.testfileLoc2)).get
    val s3Metadata = s3Blob.getMetadata(f.testfilePath2).get

    val workingDir = System.getProperty("user.dir")
    val testDir = workingDir + "/src/test/scala/ai/verta/blobs/testdir/testsubdir/testfile2"
    val pathBlob = PathBlob(List(testDir)).get
    val pathBlobMetadata = pathBlob.getMetadata(testDir).get

    assert(s3Metadata.md5 equals pathBlobMetadata.md5)
    assert(s3Metadata.size equals pathBlobMetadata.size)
  }
}
