package net.zhenglai.finatra

import com.google.inject.Stage
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class HelloWorldStartupTest extends FeatureTest {
  override protected def server = new EmbeddedHttpServer(
    twitterServer = new HelloWorldServer,
    stage = Stage.PRODUCTION,
    verbose = false
  )

  "HelloWorldServer" should {
    "startup" in {
      server.assertHealthy()
      server.assertStarted()
    }
  }
}
