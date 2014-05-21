package models

import org.joda.time.DateTime

case class Planificacion(id: Long, diaDeSemana: String, fecha: DateTime, estaPlanificado: Boolean)
