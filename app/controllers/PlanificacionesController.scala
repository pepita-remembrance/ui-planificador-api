package controllers

import play.api.mvc._
import play.api.libs.json._
import org.joda.time.DateTime
import utils.{JsonWriters, CorsAction}
import models.{Error, Planificacion}

object PlanificacionesController extends Controller with JsonWriters {
  val planificaciones = Seq(
    Planificacion(1, "Lunes", DateTime.now(), false),
    Planificacion(2, "Martes", DateTime.now(), false),
    Planificacion(3, "Miercoles", DateTime.now(), true)
  )

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