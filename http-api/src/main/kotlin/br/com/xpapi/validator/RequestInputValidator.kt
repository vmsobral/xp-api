package br.com.xpapi.validator

import br.com.xpapi.input.v1.BankConnectionInputV1
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class RequestInputValidator {

    val log = LoggerFactory.getLogger(RequestInputValidator::class.java)!!

    fun validate(requestData: br.com.xpapi.input.v1.BankConnectionInputV1?) {
        log.info("Validating RequestData.")

        if (requestData == null) {
            throw IllegalArgumentException ("RequestData cannot be null.")
        }

        if (requestData.accountInput == null) {
            throw IllegalArgumentException ("Account cannot be null.")
        }

//        if (requestData.proxyInput == null) {
//            throw IllegalArgumentException ("DefaultProxy cannot be null.")
//        }
    }
}
