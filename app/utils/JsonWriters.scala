package utils

import play.api.libs.json.{JsPath, Writes}
import org.joda.time.DateTime
import play.api.libs.functional.syntax._
import models.Planificacion

trait JsonWriters {
  implicit val planificacionWrites: Writes[Planificacion] = (
    (JsPath \ "id").write[Long] and
    (JsPath \ "diaDeSemana").write[String] and
    (JsPath \ "fecha").write[DateTime] and
    (JsPath \ "estaPlanificado").write[Boolean]
  )(unlift(Planificacion.unapply))

  implicit val errorWrites: Writes[models.Error] = (
    (JsPath \ "reason").write[String] and
    (JsPath \ "message").write[String]
  )(unlift(models.Error.unapply))
}
