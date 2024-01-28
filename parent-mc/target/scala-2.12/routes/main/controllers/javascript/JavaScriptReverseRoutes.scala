// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/DELL/Desktop/Play-Microservice/parent-mc/conf/routes
// @DATE:Sun Jan 28 23:25:22 IST 2024

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:26
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.delete",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/delete"})
        }
      """
    )
  
    // @LINE:16
    def saveComputer: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.saveComputer",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "computers"})
        }
      """
    )
  
    // @LINE:11
    def list: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.list",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "computers"})
        }
      """
    )
  
    // @LINE:24
    def pdfDownload: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.pdfDownload",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "pdf/download/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:23
    def pdfCreateUpload: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.pdfCreateUpload",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "pdf/create/upload/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:29
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:29
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
