package controllers

import play.api.mvc._
import play.api.libs.json._
import utils.{JsonWriters, CorsAction}
import models.{PlanificacionDto, Error}
import homes.PlanificacionesDeLaSemana

object PlanificacionesController extends Controller with JsonWriters {
  val home = PlanificacionesDeLaSemana

  private def toJson(planificacion : PlanificacionDto) = Json.toJson(planificacion)
  private def toJson(planificaciones : List[PlanificacionDto]) = Json.toJson(planificaciones)

  def all = CorsAction {
    Ok(toJson(home.all.map(PlanificacionDto.toDto(_))))
  }

  def getByDayOfWeek(dayOfWeek: Int) = CorsAction {
    dayOfWeek match {
      case x if 0 until 10 contains x => {
        val planificacion = home.getByDayOfWeek(dayOfWeek)
        Ok(toJson(planificacion))
      }

      case _ => BadRequest(Json.toJson(Error("Dia invalido", "El dia de la semana debe ser un numero entre 1 y 7")))
    }
  }

}