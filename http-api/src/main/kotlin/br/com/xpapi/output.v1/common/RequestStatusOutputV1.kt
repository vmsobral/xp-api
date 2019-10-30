package br.com.xpapi.output.v1.common

import br.com.xpapi.mobileapi.dto.Status

data class RequestStatusOutputV1(
        val message: String
)

fun Status.toOutputV1() = RequestStatusOutputV1(this.name)