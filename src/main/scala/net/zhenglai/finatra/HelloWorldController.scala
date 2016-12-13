package net.zhenglai.finatra

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class HelloWorldController extends Controller {

  get("/hi") { request: Request =>
    info("hi")
    "Hello " + request.params.getOrElse("name", "unamed")
  }

  post("/hi") {hiRequest: HiRequest =>
    "Hello "+ hiRequest.name + " with id " + hiRequest.id + " and " + hiRequest.age.fold("age missing")("age " + _)
  }
}

case class HiRequest(
  id: Long,
  name: String,
  age: Option[Int]
)
