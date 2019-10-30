package br.com.xpapi.controller

import org.springframework.stereotype.Component
import ro.pippo.controller.Controller
import ro.pippo.controller.GET
import ro.pippo.controller.Path
import ro.pippo.controller.Produces

@Path("/")
@Component
class SystemController : Controller() {

    @GET(value = ["/", "/health"])
    @Produces(Produces.JSON)
    fun health(): Map<String, Boolean> {
        return mapOf("status" to true)
    }

}