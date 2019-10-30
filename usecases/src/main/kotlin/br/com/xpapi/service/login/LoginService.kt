package br.com.xpapi.service.login

import br.com.xpapi.mobileapi.connector.XPRetrofitConnector
import br.com.xpapi.mobileapi.dto.LoginResponse
import br.com.xpapi.mobileapi.dto.RequestData

interface LoginService {

    fun login(requestData: RequestData, connector: XPRetrofitConnector): LoginResponse

}
