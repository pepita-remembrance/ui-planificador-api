package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import org.joda.time.DateTime
import utils.CorsAction

case class Planificacion(diaDeSemana: String, fecha: DateTime, estaPlanificado: Boolean);

object Application extends Controller {

  implicit val planificacionWrites: Writes[Planificacion] = (
    (JsPath \ "diaDeSemana").write[String] and
    (JsPath \ "fecha").write[DateTime] and
    (JsPath \ "estaPlanificado").write[Boolean]
  )(unlift(Planificacion.unapply))

  def index = CorsAction {
    val planificaciones = Seq(
      Planificacion("Lunes", DateTime.now(), false),
      Planificacion("Martes", DateTime.now(), false),
      Planificacion("Miercoles", DateTime.now(), true)
    )

    Ok(Json.toJson(planificaciones))
  }

}