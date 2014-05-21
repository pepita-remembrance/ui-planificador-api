package controllers

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import org.joda.time.DateTime
import utils.CorsAction

case class Planificacion(id: Long, diaDeSemana: String, fecha: DateTime, estaPlanificado: Boolean);

case class Error(reason: String, message: String)

object PlanificacionesController extends Controller {

  val planificaciones = Seq(
    Planificacion(1, "Lunes", DateTime.now(), false),
    Planificacion(2, "Martes", DateTime.now(), false),
    Planificacion(3, "Miercoles", DateTime.now(), true)
  )

  implicit val planificacionWrites: Writes[Planificacion] = (
    (JsPath \ "id").write[Long] and
    (JsPath \ "diaDeSemana").write[String] and
    (JsPath \ "fecha").write[DateTime] and
    (JsPath \ "estaPlanificado").write[Boolean]
  )(unlift(Planificacion.unapply))

  implicit val errorWrites: Writes[Error] = (
    (JsPath \ "reason").write[String] and
    (JsPath \ "message").write[String]
  )(unlift(Error.unapply))

  def all = CorsAction {
    Ok(Json.toJson(planificaciones))
  }

  def getById(id: Long) = CorsAction {
    planificaciones.find(_.id == id) match {
      case Some(planificacion) => Ok(Json.toJson(planificacion))
      case None => NotFound(Json.toJson(Error("Not found", s"La planificacion #$id no existe")))
    }
  }

}