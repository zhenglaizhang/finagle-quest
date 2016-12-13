package net.zhenglai.finatra

import com.twitter.finagle.http.Status._
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

/**
  * Feature test approach
  */
class HelloWorldFeatureTest extends FeatureTest {
  override protected def server = new EmbeddedHttpServer(new HelloWorldServer)

  "HelloWorldServer" should {
    "Say hi" in {
      server.httpGet(
        path = "/hi?name=foo",
        andExpect = Ok,
        withBody = "Hello foo"
      )
    }
    
    "Say hi for Post without age" in {
      server.httpPost(
        path = "/hi",
        postBody =
          """
            |{
            |  "id": 10,
            |  "name": "bar"
            |}
          """.stripMargin,
        andExpect = Ok,
        withBody = "Hello bar with id 10 and age missing"
      )
    }

    "Say hi for Post with age" in {
      server.httpPost(
        path = "/hi",
        postBody =
          """
            |{
            | "id": 20,
            | "name": "meow",
            | "age": 30
            |}
          """.stripMargin,
        andExpect = Ok,
        withBody = "Hello meow with id 20 and age 30"
      )
    }
  }
}
