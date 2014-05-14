import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}

object CorsAction extends ActionBuilder[Request] with Results {
  val MaxAge = 60 * 60 * 24 * 30

  val AllowHeaders = List(
    "Accept", "Origin", "Content-type", "Authorization", "X-Auth-Token",
    "X-HTTP-Method-Override", "X-Json", "X-Prototype-Version", "X-Requested-With")

  val AllowMethods = List("GET", "POST", "PUT", "DELETE", "OPTIONS")

  val AllowCredentials = true

  def cors[A](origin: String) =
    Ok.withHeaders(
      "Access-Control-Allow-Origin" -> origin,
      "Access-Control-Allow-Methods" -> AllowMethods.mkString(", "),
      "Access-Control-Allow-Headers" -> AllowHeaders.mkString(", "),
      "Access-Control-Allow-Credentials" -> AllowCredentials.toString,
      "Access-Control-Max-Age" -> MaxAge.toString)

  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[SimpleResult]) = {
    implicit val executionContext = play.api.libs.concurrent.Execution.defaultContext
    val origin = request.headers.get("Origin").getOrElse("*")

    if (request.method == "OPTIONS") {
      Future.successful(cors(origin))
    } else {
      block(request).map(res =>
        res.withHeaders(
          "Access-Control-Allow-Origin" -> origin,
          "Access-Control-Allow-Credentials" -> AllowCredentials.toString
        ))
    }
  }
}