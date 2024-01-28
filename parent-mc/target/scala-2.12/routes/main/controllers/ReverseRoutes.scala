// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/DELL/Desktop/Play-Microservice/parent-mc/conf/routes
// @DATE:Sun Jan 28 23:25:22 IST 2024

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:26
    def delete(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/delete")
    }
  
    // @LINE:16
    def saveComputer(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "computers")
    }
  
    // @LINE:11
    def list(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "computers")
    }
  
    // @LINE:24
    def pdfDownload(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "pdf/download/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:23
    def pdfCreateUpload(id:Long): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "pdf/create/upload/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:29
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:29
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
