package br.com.xpapi.service.investment

import br.com.xpapi.mobileapi.connector.XPRetrofitConnector
import br.com.xpapi.mobileapi.dto.Investments
import br.com.xpapi.mobileapi.dto.RequestData

interface InvestmentService {

    fun investment(requestData: RequestData, connector: XPRetrofitConnector): Investments

}
