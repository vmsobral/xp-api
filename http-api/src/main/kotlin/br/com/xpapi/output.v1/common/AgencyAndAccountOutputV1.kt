package br.com.xpapi.output.v1.common

import br.com.xpapi.mobileapi.dto.BranchAccount
import com.google.gson.annotations.SerializedName

data class AgencyAndAccountOutputV1(
        @SerializedName("agency") val branch: String?,
        val account: String?,
        @SerializedName("accountDigit") val digit: String?
)

fun BranchAccount.toOutputV1() = AgencyAndAccountOutputV1(
        branch = this.branch,
        account = this.account,
        digit = this.digit
)