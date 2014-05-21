package homes

import models.Planificacion
import org.joda.time.DateTime

class DummyPlanificacionesHome extends Home[Planificacion] {
  override def all = Seq(
    Planificacion(1, "Lunes", DateTime.now(), false),
    Planificacion(2, "Martes", DateTime.now(), false),
    Planificacion(3, "Miercoles", DateTime.now(), true)
  )

  override def getById(id: Long) = all().find(_.id == id)
}
