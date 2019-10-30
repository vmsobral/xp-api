package br.com.xpapi.mobileapi.connector

class XPRetrofitConnector(
        val connector: IRetrofitBankingConnector,
        var authorization: String
)