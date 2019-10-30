package br.com.xpapi.mobileapi.connector.executor

import br.com.xpapi.exceptions.ConnectorException
import br.com.xpapi.mobileapi.connector.XPRetrofitConnector
import br.com.xpapi.mobileapi.dto.Positions
import br.com.xpapi.mobileapi.dto.RequestData
import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MobileApiInvestmentConnectionExecutor {

    companion object {
        private val log = LoggerFactory.getLogger(MobileApiInvestmentConnectionExecutor::class.java)
    }

    fun positions(requestData: RequestData,
                  xpConnector: XPRetrofitConnector
    ): Positions {
        log.info("Init positions")

        val positionsResponse = xpConnector
                .connector
                .positions("Bearer " + xpConnector.authorization)
                .execute()

        val jsonResponse = Gson().toJson(positionsResponse)

        if (!positionsResponse.isSuccessful) throw ConnectorException("Exception executing positions")

        return positionsResponse.body()!!
    }

}
