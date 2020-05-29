// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.swagger._public.uac.model

import scala.util.Try

import net.liftweb.json._

import ai.verta.swagger._public.uac.model.ServiceEnumService._
import ai.verta.swagger._public.uac.model.ModelResourceEnumModelDBServiceResourceTypes._
import ai.verta.swagger._public.uac.model.ModelDBActionEnumModelDBServiceActions._
import ai.verta.swagger.client.objects._

case class UacSetRoleBindingResponse (
  role_binding: Option[UacRoleBinding] = None
) extends BaseSwagger {
  def toJson(): JValue = UacSetRoleBindingResponse.toJson(this)
}

object UacSetRoleBindingResponse {
  def toJson(obj: UacSetRoleBindingResponse): JObject = {
    new JObject(
      List[Option[JField]](
        obj.role_binding.map(x => JField("role_binding", ((x: UacRoleBinding) => UacRoleBinding.toJson(x))(x)))
      ).flatMap(x => x match {
        case Some(y) => List(y)
        case None => Nil
      })
    )
  }

  def fromJson(value: JValue): UacSetRoleBindingResponse =
    value match {
      case JObject(fields) => {
        val fieldsMap = fields.map(f => (f.name, f.value)).toMap
        UacSetRoleBindingResponse(
          // TODO: handle required
          role_binding = fieldsMap.get("role_binding").map(UacRoleBinding.fromJson)
        )
      }
      case _ => throw new IllegalArgumentException(s"unknown type ${value.getClass.toString}")
    }
}
