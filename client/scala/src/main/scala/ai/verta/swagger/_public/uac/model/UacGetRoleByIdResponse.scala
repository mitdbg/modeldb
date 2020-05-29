// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.swagger._public.uac.model

import scala.util.Try

import net.liftweb.json._

import ai.verta.swagger._public.uac.model.ServiceEnumService._
import ai.verta.swagger._public.uac.model.ModelResourceEnumModelDBServiceResourceTypes._
import ai.verta.swagger._public.uac.model.ModelDBActionEnumModelDBServiceActions._
import ai.verta.swagger.client.objects._

case class UacGetRoleByIdResponse (
  role: Option[UacRole] = None
) extends BaseSwagger {
  def toJson(): JValue = UacGetRoleByIdResponse.toJson(this)
}

object UacGetRoleByIdResponse {
  def toJson(obj: UacGetRoleByIdResponse): JObject = {
    new JObject(
      List[Option[JField]](
        obj.role.map(x => JField("role", ((x: UacRole) => UacRole.toJson(x))(x)))
      ).flatMap(x => x match {
        case Some(y) => List(y)
        case None => Nil
      })
    )
  }

  def fromJson(value: JValue): UacGetRoleByIdResponse =
    value match {
      case JObject(fields) => {
        val fieldsMap = fields.map(f => (f.name, f.value)).toMap
        UacGetRoleByIdResponse(
          // TODO: handle required
          role = fieldsMap.get("role").map(UacRole.fromJson)
        )
      }
      case _ => throw new IllegalArgumentException(s"unknown type ${value.getClass.toString}")
    }
}
