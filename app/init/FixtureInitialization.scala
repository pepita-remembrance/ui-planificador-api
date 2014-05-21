package init

import play.api.{Application, GlobalSettings}
import edu.unq.uis.planificador.domain.Empleado
import edu.unq.uis.planificador.domain.disponibilidad.Turno
import edu.unq.uis.planificador.dependencyInjection.DevEnvironment

object FixtureInitialization extends GlobalSettings with DevEnvironment {
  def createEmpleadosFixture() = {
    val pedro = new Empleado("Pedro", "Picapiedras", "123134")
    pedro asignar (Turno el "2014-05-19" de 14 a 18)
    create(pedro)

    val juana = new Empleado("juana", "Picapiedras", "8795468")
    juana asignar (Turno el "2014-05-19" de 15 a 21)
    juana asignar (Turno el "2014-05-20" de 15 a 21)
    create(juana)
  }

  def create(empleado: Empleado) {
    empleadoHome.create(empleado)
  }

  override def onStart(app: Application) {
    createEmpleadosFixture()
  }
}
