package br.com.xpapi.mobileapi

import br.com.xpapi.mobileapi.connector.RetrofitConnectorFactory
import br.com.xpapi.mobileapi.dto.LoginResponse
import br.com.xpapi.mobileapi.dto.RequestData
import br.com.xpapi.mobileapi.dto.Status
import br.com.xpapi.mobileapi.dto.UserData
import br.com.xpapi.mobileapi.service.MobileApiInvestmentService
import br.com.xpapi.mobileapi.service.MobileApiLoginService
import br.com.xpapi.service.RetrieveService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MobileApiRetrieveService(
        private val mobileApiLoginService: MobileApiLoginService,
        private val mobileApiInvestmentService: MobileApiInvestmentService,
        private val retrofitConnectorFactory: RetrofitConnectorFactory
) : RetrieveService {

    companion object {
        private val log = LoggerFactory.getLogger(MobileApiRetrieveService::class.java)
    }

    override fun retrieve(requestData: RequestData): UserData {
        log.info("Starting Retrieve - Initializing connector")
        val connector = retrofitConnectorFactory.create()

        val retrieveResponse = UserData(Status.SUCCESS)
        try {
            val userData = UserData()
            log.info("Starting Login")
            userData.setLoginData(mobileApiLoginService.login(requestData, connector))

            log.info("Retrieving Investments")
            userData.investments = mobileApiInvestmentService.investment(requestData, connector)

            retrieveResponse.setUserData(userData)
        } catch (e: Exception) {
            log.error("Error executing Retrieve", e)
        }

        return retrieveResponse
    }

    private fun UserData.setLoginData(loginData: LoginResponse) {
        this.cpf = loginData.cpf
        this.branchAccount = loginData.branchAccount
        this.users = loginData.users
    }

    private fun UserData.setUserData(userData: UserData) {
        this.cpf = userData.cpf
        this.branchAccount = userData.branchAccount
        this.users = userData.users
        this.investments = userData.investments
    }
}