// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/DELL/Desktop/Play-Microservice/parent-mc/conf/routes
// @DATE:Sun Jan 28 23:25:22 IST 2024

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_1: controllers.HomeController,
  // @LINE:29
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_1: controllers.HomeController,
    // @LINE:29
    Assets_0: controllers.Assets
  ) = this(errorHandler, HomeController_1, Assets_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """computers""", """controllers.HomeController.list()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """computers""", """controllers.HomeController.saveComputer(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pdf/create/upload/""" + "$" + """id<[^/]+>""", """controllers.HomeController.pdfCreateUpload(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pdf/download/""" + "$" + """id<[^/]+>""", """controllers.HomeController.pdfDownload(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """""" + "$" + """id<[^/]+>/delete""", """controllers.HomeController.delete(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_1.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_HomeController_list1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("computers")))
  )
  private[this] lazy val controllers_HomeController_list1_invoker = createInvoker(
    HomeController_1.list(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "list",
      Nil,
      "GET",
      this.prefix + """computers""",
      """ Computers list (look at the default values for pagination parameters)""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_HomeController_saveComputer2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("computers")))
  )
  private[this] lazy val controllers_HomeController_saveComputer2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_1.saveComputer(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "saveComputer",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """computers""",
      """ Add computer
GET     /computers/new              controllers.HomeController.create(request: Request)""",
      Seq()
    )
  )

  // @LINE:23
  private[this] lazy val controllers_HomeController_pdfCreateUpload3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pdf/create/upload/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_pdfCreateUpload3_invoker = createInvoker(
    HomeController_1.pdfCreateUpload(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "pdfCreateUpload",
      Seq(classOf[Long]),
      "POST",
      this.prefix + """pdf/create/upload/""" + "$" + """id<[^/]+>""",
      """PDF create & Download""",
      Seq()
    )
  )

  // @LINE:24
  private[this] lazy val controllers_HomeController_pdfDownload4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pdf/download/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_pdfDownload4_invoker = createInvoker(
    HomeController_1.pdfDownload(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "pdfDownload",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """pdf/download/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:26
  private[this] lazy val controllers_HomeController_delete5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), DynamicPart("id", """[^/]+""",true), StaticPart("/delete")))
  )
  private[this] lazy val controllers_HomeController_delete5_invoker = createInvoker(
    HomeController_1.delete(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "delete",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """""" + "$" + """id<[^/]+>/delete""",
      """ Delete a computer""",
      Seq()
    )
  )

  // @LINE:29
  private[this] lazy val controllers_Assets_versioned6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned6_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_1.index)
      }
  
    // @LINE:11
    case controllers_HomeController_list1_route(params@_) =>
      call { 
        controllers_HomeController_list1_invoker.call(HomeController_1.list())
      }
  
    // @LINE:16
    case controllers_HomeController_saveComputer2_route(params@_) =>
      call { 
        controllers_HomeController_saveComputer2_invoker.call(
          req => HomeController_1.saveComputer(req))
      }
  
    // @LINE:23
    case controllers_HomeController_pdfCreateUpload3_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_HomeController_pdfCreateUpload3_invoker.call(HomeController_1.pdfCreateUpload(id))
      }
  
    // @LINE:24
    case controllers_HomeController_pdfDownload4_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_HomeController_pdfDownload4_invoker.call(HomeController_1.pdfDownload(id))
      }
  
    // @LINE:26
    case controllers_HomeController_delete5_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_HomeController_delete5_invoker.call(HomeController_1.delete(id))
      }
  
    // @LINE:29
    case controllers_Assets_versioned6_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned6_invoker.call(Assets_0.versioned(path, file))
      }
  }
}
