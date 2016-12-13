package net.zhenglai.finatra

import com.twitter.finagle.http.Status
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{ EmbeddedHttpServer, HttpServer }
import com.twitter.inject.server.FeatureTest

/**
  * Feature Test first, no prod code yet
  */
class PopularApiFeatureTest extends FeatureTest {
  override protected def server = new EmbeddedHttpServer(new HttpServer {
    override protected def configureHttp(router: HttpRouter) = ???
  })

  "Handle POST request" in {
    server.httpPost(
      path = "/api/popular",
      headers = Map("api_key" -> "secret123"),
      postBody = """{"start": "2015-09-01", "end":"2016-01-01"}""",
      andExpect = Status.Ok,
      withJsonBody =
        """
          |{
          | "start": "2015-09-01T00:00:00Z
          | "end": "2015-09-01T00:00:00Z
          | "tweets": [...]
          |}
        """.stripMargin
    )
  }

}
