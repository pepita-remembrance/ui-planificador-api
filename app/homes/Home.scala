package homes

trait Home[T] {
  def all() : Seq[T]
  def getById(id: Long) : Option[T]
}
