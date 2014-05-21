package controllers

import play.api.mvc._
import play.api.libs.json._
import utils.{JsonWriters, CorsAction}
import models.Error
import homes.DummyPlanificacionesHome

object PlanificacionesController extends Controller with JsonWriters {
  val home = new DummyPlanificacionesHome()

  def all = CorsAction {
    Ok(Json.toJson(home.all))
  }

  def getById(id: Long) = CorsAction {
    home.getById(id) match {
      case Some(planificacion) => Ok(Json.toJson(planificacion))
      case None => NotFound(Json.toJson(Error("Not found", s"La planificacion #$id no existe")))
    }
  }

}