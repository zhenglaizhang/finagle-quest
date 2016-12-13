package net.zhenglai.finatra

import com.twitter.finagle.http.Status._
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class HelloWorldControllerTest extends FeatureTest {
  override protected def server = new EmbeddedHttpServer(new HelloWorldServer)

  "HelloWorldServer" should {
    "Say hi" in {
      server.httpGet(
        path = "/hi?name=foo",
        andExpect = Ok,
        withBody = "Hello foo"
      )
    }
    
    "Say hi for Post" in {
      // TODO:  
    }
  }
}
