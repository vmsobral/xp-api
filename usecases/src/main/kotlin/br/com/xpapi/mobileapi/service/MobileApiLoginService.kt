package br.com.xpapi.mobileapi.service

import br.com.xpapi.mobileapi.connector.XPRetrofitConnector
import br.com.xpapi.mobileapi.connector.executor.MobileApiLoginConnectionExecutor
import br.com.xpapi.mobileapi.dto.LoginResponse
import br.com.xpapi.mobileapi.dto.RequestData
import br.com.xpapi.mobileapi.parser.MobileApiLoginParser
import br.com.xpapi.service.login.LoginService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MobileApiLoginService(
        private val connectionExecutor: MobileApiLoginConnectionExecutor,
        private val parser: MobileApiLoginParser
) : LoginService {

    companion object {
        private val log = LoggerFactory.getLogger(MobileApiLoginService::class.java)
    }

    override fun login(requestData: RequestData, connector: XPRetrofitConnector): LoginResponse {

        log.info("Doing accounts request")
        val accountsResponse = connectionExecutor.accounts(requestData, connector)
        log.info("Success accounts")

        log.info("Doing search client request")
        val keyboardResponse = connectionExecutor.keyboard(requestData, connector, accountsResponse)
        log.info("Success searching client")

        log.info("Doing authenticate request")
        val authenticateResponse = connectionExecutor.authenticate(requestData, connector, keyboardResponse)
        log.info("Success authenticating")

        return LoginResponse(
            users = parser.parseUsersList(accountsResponse),
//            cpf = parser.parseCpf(accountsResponse.body()!!)
            branchAccount = parser.parseAgencyAccount(accountsResponse)
        )
    }
}
