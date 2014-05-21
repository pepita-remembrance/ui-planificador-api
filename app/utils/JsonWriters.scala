package utils

import play.api.libs.json.{JsPath, Writes}
import org.joda.time.DateTime
import play.api.libs.functional.syntax._
import models.{EmpleadoDto, PlanificacionDto}

trait JsonWriters {
  implicit val empleadoWrites: Writes[EmpleadoDto] = (
    (JsPath \ "nombre").write[String] and
    (JsPath \ "entrada").write[String] and
    (JsPath \ "salida").write[String]
  )(unlift(EmpleadoDto.unapply))

  implicit val planificacionWrites: Writes[PlanificacionDto] = (
    (JsPath \ "fecha").write[DateTime] and
    (JsPath \ "estaPlanificado").write[Boolean] and
    (JsPath \ "empleados").write[Seq[EmpleadoDto]]
  )(unlift(PlanificacionDto.unapply))

  implicit val errorWrites: Writes[models.Error] = (
    (JsPath \ "reason").write[String] and
    (JsPath \ "message").write[String]
  )(unlift(models.Error.unapply))
}
