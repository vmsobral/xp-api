package br.com.xpapi.mobileapi.service

import br.com.xpapi.mobileapi.connector.XPRetrofitConnector
import br.com.xpapi.mobileapi.connector.executor.MobileApiInvestmentConnectionExecutor
import br.com.xpapi.mobileapi.dto.Investments
import br.com.xpapi.mobileapi.dto.RequestData
import br.com.xpapi.mobileapi.parser.MobileApiInvestmentParser
import br.com.xpapi.service.investment.InvestmentService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MobileApiInvestmentService(
        private val connectionExecutor: MobileApiInvestmentConnectionExecutor,
        private val parser: MobileApiInvestmentParser
) : InvestmentService {

    companion object {
        private val log = LoggerFactory.getLogger(MobileApiInvestmentService::class.java)
    }

    override fun investment(requestData: RequestData, connector: XPRetrofitConnector): Investments {
        val investment = Investments()

        log.info("Doing positions request")
        val positionsResponse = connectionExecutor.positions(requestData, connector)
        log.info("Success retrieving positions")

        investment.investmentFunds = parser.parseInvestmentPositions(positionsResponse)

        return investment
    }
}
