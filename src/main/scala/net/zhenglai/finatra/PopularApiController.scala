package net.zhenglai.finatra

import com.google.inject.Inject
import com.twitter.finagle.http.{ Request, Response }
import com.twitter.finatra.http.filters.{ LoggingMDCFilter, TraceIdMDCFilter }
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{ Controller, HttpServer }
import com.twitter.inject.TwitterModule

class PopularApiController @Inject()(
  userRepository: UserRepository,
  searchService: SearchService,
  engagementService: EngagementService
) extends Controller {
  post("/api/popular") { request: PopularPostRequest =>

  }
}

trait UserRepository

trait SearchService

trait EngagementService

object UserRepositoryModule extends TwitterModule
object SearchServiceModule extends TwitterModule
object EngagementServiceModule extends TwitterModule

class PopularApiServer extends HttpServer {
  override var modules = Seq(
    UserRepositoryModule,
    SearchServiceModule,
    EngagementServiceModule
  )

  override def configureHttp(router: HttpRouter) = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .add[PopularApiController]
  }
}
