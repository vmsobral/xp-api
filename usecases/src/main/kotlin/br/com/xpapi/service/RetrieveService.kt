package br.com.xpapi.service

import br.com.xpapi.mobileapi.dto.RequestData
import br.com.xpapi.mobileapi.dto.UserData

interface RetrieveService {

    fun retrieve(requestData: RequestData): UserData

}
