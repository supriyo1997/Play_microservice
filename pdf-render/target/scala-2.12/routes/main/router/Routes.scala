// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/DELL/Desktop/Play-Microservice/pdf-render/conf/routes
// @DATE:Sun Jan 28 23:26:37 IST 2024

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_2: controllers.HomeController,
  // @LINE:7
  PdfController_0: controllers.PdfController,
  // @LINE:10
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_2: controllers.HomeController,
    // @LINE:7
    PdfController_0: controllers.PdfController,
    // @LINE:10
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_2, PdfController_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_2, PdfController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """createPdf""", """controllers.PdfController.generateAndStorePdf(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """downloadPdf/""" + "$" + """id<[^/]+>""", """controllers.PdfController.downloadPdf(id:Long)"""),
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
    HomeController_2.index,
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

  // @LINE:7
  private[this] lazy val controllers_PdfController_generateAndStorePdf1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("createPdf")))
  )
  private[this] lazy val controllers_PdfController_generateAndStorePdf1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      PdfController_0.generateAndStorePdf(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PdfController",
      "generateAndStorePdf",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """createPdf""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_PdfController_downloadPdf2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("downloadPdf/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_PdfController_downloadPdf2_invoker = createInvoker(
    PdfController_0.downloadPdf(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PdfController",
      "downloadPdf",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """downloadPdf/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_Assets_versioned3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned3_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
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
        controllers_HomeController_index0_invoker.call(HomeController_2.index)
      }
  
    // @LINE:7
    case controllers_PdfController_generateAndStorePdf1_route(params@_) =>
      call { 
        controllers_PdfController_generateAndStorePdf1_invoker.call(
          req => PdfController_0.generateAndStorePdf(req))
      }
  
    // @LINE:8
    case controllers_PdfController_downloadPdf2_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_PdfController_downloadPdf2_invoker.call(PdfController_0.downloadPdf(id))
      }
  
    // @LINE:10
    case controllers_Assets_versioned3_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned3_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
