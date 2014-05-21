package homes

import org.joda.time.{DateTimeConstants, LocalDate}
import edu.unq.uis.planificador.domain.Planificacion

object PlanificacionesDeLaSemana {
  val now = new LocalDate()
  val planificaciones = List(
    Planificacion(now.withDayOfWeek(DateTimeConstants.MONDAY).toDateTimeAtStartOfDay),
    Planificacion(now.withDayOfWeek(DateTimeConstants.TUESDAY).toDateTimeAtStartOfDay),
    Planificacion(now.withDayOfWeek(DateTimeConstants.WEDNESDAY).toDateTimeAtStartOfDay),
    Planificacion(now.withDayOfWeek(DateTimeConstants.THURSDAY).toDateTimeAtStartOfDay),
    Planificacion(now.withDayOfWeek(DateTimeConstants.FRIDAY).toDateTimeAtStartOfDay),
    Planificacion(now.withDayOfWeek(DateTimeConstants.SATURDAY).toDateTimeAtStartOfDay),
    Planificacion(now.withDayOfWeek(DateTimeConstants.SUNDAY).toDateTimeAtStartOfDay)
  )

  def all = planificaciones

  def getByDayOfWeek(dayOfWeek: Int): Planificacion =
    all.find(_.fecha.dayOfWeek.get.equals(dayOfWeek)).get
}
