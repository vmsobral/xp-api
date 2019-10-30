package br.com.xpapi.mobileapi.connector.executor

import br.com.xpapi.exceptions.ConnectorException
import br.com.xpapi.mobileapi.connector.XPRetrofitConnector
import br.com.xpapi.mobileapi.dto.KeyboardKeys
import br.com.xpapi.mobileapi.dto.KeyboardOption
import br.com.xpapi.mobileapi.dto.RequestData
import br.com.xpapi.mobileapi.dto.XPAccount
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MobileApiLoginConnectionExecutor {

    companion object {
        private val log = LoggerFactory.getLogger(MobileApiLoginConnectionExecutor::class.java)
    }

    fun accounts(requestData: RequestData, xpConnector: XPRetrofitConnector): XPAccount {
        log.info("Init accounts")

        val accountsResponse = xpConnector.connector.accounts(requestData.account.fullAccount()).execute()

        if (!accountsResponse.isSuccessful) throw ConnectorException("Exception executing accounts")

        xpConnector.authorization = accountsResponse.headers().get("X-Authorization")!!

        return accountsResponse.body()!!
    }

    fun keyboard(requestData: RequestData,
                 xpConnector: XPRetrofitConnector,
                 accountsResponse: XPAccount
    ): List<KeyboardOption> {
        log.info("Init keyboard")

        val keyboardResponse = xpConnector
                .connector
                .keyboard("Bearer " + xpConnector.authorization)
                .execute()

        if (!keyboardResponse.isSuccessful) throw ConnectorException("Exception executing keyboard")

        xpConnector.authorization = keyboardResponse.headers().get("X-Authorization")!!

        return keyboardResponse.body()!!
    }

    fun authenticate(requestData: RequestData,
                     xpConnector: XPRetrofitConnector,
                     keyboardResponse: List<KeyboardOption>
    ) {
        log.info("Init authenticate")

        val authenticateResponse = xpConnector
                .connector
                .authenticate(
                        findKeyboardKeys(requestData.account.password, keyboardResponse),
                        "Bearer " + xpConnector.authorization)
                .execute()

        if (!authenticateResponse.isSuccessful) throw ConnectorException("Exception executing authenticate")

        xpConnector.authorization = authenticateResponse.headers().get("X-Authorization")!!
    }

    private fun findKeyboardKeys(password: String, body: List<KeyboardOption>): KeyboardKeys {
        val keysList = mutableListOf<String>()
        for (char in password) {
            val passDigit = Character.getNumericValue(char)
            for (keyboardOption in body) {
                if (keyboardOption.options.contains(passDigit)) {
                    keysList.add(keyboardOption.id)
                    break
                }
            }
        }
        return KeyboardKeys(keysList)
    }
}
