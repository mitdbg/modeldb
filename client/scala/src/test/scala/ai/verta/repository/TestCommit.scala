package ai.verta.repository

import ai.verta.client._
import ai.verta.blobs._
import ai.verta.blobs.dataset._

import scala.concurrent.ExecutionContext
import scala.language.reflectiveCalls
import scala.util.{Try, Success, Failure}

import org.scalatest.FunSuite
import org.scalatest.Assertions._

class TestCommit extends FunSuite {
  implicit val ec = ExecutionContext.global

  def fixture =
    new {
        val client = new Client(ClientConnection.fromEnvironment())
        val repo = client.getOrCreateRepository("My Repo").get
        val commit = repo.getCommitByBranch().get
        val pathBlob = PathBlob(f"${System.getProperty("user.dir")}/src/test/scala/ai/verta/blobs/testdir").get
        val s3Blob = S3(S3Location("s3://verta-scala-test/testdir/testsubdir/testfile2").get).get
    }

  def cleanup(
    f: AnyRef{val client: Client; val repo: Repository; val commit: Commit; val pathBlob: PathBlob; val s3Blob: S3}
  ) = {
    f.client.deleteRepository(f.repo.id)
    f.client.close()
  }

  test("Get should retrieve blobs that were updated") {
    val f = fixture

    try {
      val newCommit = f.commit.update("abc/def", f.pathBlob)
                              .flatMap(_.update("mnp/qrs", f.s3Blob)).get

      // check that the contents of the blobs are not corrupted:
      val pathBlob2 = newCommit.get("abc/def").get match {
        case blob: PathBlob => blob
        case blob: S3 => blob
      }
      assert(pathBlob2 equals f.pathBlob)

      val s3Blob2 = newCommit.get("mnp/qrs").get match {
        case blob: PathBlob => blob
        case blob: S3 => blob
      }
      assert(s3Blob2 equals f.s3Blob)

      val getAttempt2 = newCommit.get("xyz/tuv")
      assert(getAttempt2.isFailure)
      assert(getAttempt2 match {case Failure(e) => e.getMessage contains "No blob was stored at this path."})
    } finally {
      cleanup(f)
    }
  }

  test("Tagging unsaved commit should fail") {
    val f = fixture

    try {
      val newCommit = f.commit.update("abc/def", f.pathBlob).get
      val tagAttempt = newCommit.tag("Some tag")
      assert(tagAttempt.isFailure)
      assert(tagAttempt match {
        case Failure(e) => e.getMessage contains "Commit must be saved before it can be tagged"
      })
    } finally {
      cleanup(f)
    }
  }

  test("newBranch unsaved commit should fail") {
    val f = fixture

    try {
      val newCommit = f.commit.update("abc/def", f.pathBlob).get
      val newBranchAttempt = newCommit.newBranch("some-branch")
      assert(newBranchAttempt.isFailure)
      assert(newBranchAttempt match {
        case Failure(e) => e.getMessage contains "Commit must be saved before it can be attached to a branch"
      })
    } finally {
      cleanup(f)
    }
  }

  test("Remove should discard blob from commit") {
    val f = fixture

    try {
      val newCommit = f.commit.update("abc/def", f.pathBlob)
                              .flatMap(_.update("mnp/qrs", f.pathBlob))
                              .flatMap(_.remove("abc/def")).get

      val getAttempt = newCommit.get("abc/def")
      assert(getAttempt.isFailure)
      assert(getAttempt match {case Failure(e) => e.getMessage contains "No blob was stored at this path."})
    } finally {
      cleanup(f)
    }
  }

  test("Remove a non-existing path should fail") {
    val f = fixture

    try {
      val removeAttempt = f.commit.remove("abc/def")
      assert(removeAttempt.isFailure)
      assert(removeAttempt match {case Failure(e) => e.getMessage contains "No blob was stored at this path."})
    } finally {
      cleanup(f)
    }
  }
}
