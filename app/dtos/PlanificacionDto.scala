package models

import org.joda.time.DateTime
import edu.unq.uis.planificador.domain.{Empleado, Planificacion}

case class PlanificacionDto(fecha: DateTime, estaPlanificado: Boolean, empleados: Seq[EmpleadoDto])
case class EmpleadoDto(nombre: String, entrada: String, salida: String)

object PlanificacionDto {
  implicit def toDto(model: Empleado) : EmpleadoDto = EmpleadoDto(model.nombreCompleto, "", "")
  implicit def toDto(model: Planificacion) : PlanificacionDto =
    PlanificacionDto(model.fecha, model.sePlanifico, model.empleados.map(toDto(_)))
}