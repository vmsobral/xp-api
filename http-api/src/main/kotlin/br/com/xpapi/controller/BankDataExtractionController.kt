package br.com.xpapi.controller

import br.com.xpapi.input.v1.BankConnectionInputV1
import br.com.xpapi.output.v1.RetrieveOutputV1
import br.com.xpapi.output.v1.toOutputV1
import br.com.xpapi.service.RetrieveService
import br.com.xpapi.validator.RequestInputValidator
import org.springframework.stereotype.Component
import ro.pippo.controller.Controller
import ro.pippo.controller.POST
import ro.pippo.controller.Path
import ro.pippo.controller.Produces
import ro.pippo.controller.extractor.Body

@Path("/")
@Component
class BankDataExtractionController(
        private val requestInputValidator: RequestInputValidator,
        private val retrieveService: RetrieveService
) : Controller() {

    @POST("/retrieve")
    @Produces(Produces.JSON)
    fun retrieve(@Body requestInput: br.com.xpapi.input.v1.BankConnectionInputV1): RetrieveOutputV1 {
        validateRequest(requestInput)
        return retrieveService.retrieve(requestInput.toRequestData()).toOutputV1()
    }

    private fun validateRequest(requestInput: br.com.xpapi.input.v1.BankConnectionInputV1) {
        requestInputValidator.validate(requestInput)
    }

}