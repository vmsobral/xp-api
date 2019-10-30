package br.com.xpapi.input.v1

import br.com.xpapi.mobileapi.dto.Account
import br.com.xpapi.mobileapi.dto.RequestData
import com.google.gson.annotations.SerializedName

data class BankConnectionInputV1(
        @SerializedName("account") val accountInput: br.com.xpapi.input.v1.AccountInputV1?
) {
    fun toRequestData() = RequestData(this@BankConnectionInputV1.accountInput!!.toAccount())
}

data class AccountInputV1(
        @SerializedName("branch") val branch: String,
        val account: String,
        @SerializedName("dac") val digit: String,
        val password: String,
        val cpf: String
) {
    fun toAccount() = Account().apply {
        this.agency = this@AccountInputV1.branch
        this.account = this@AccountInputV1.account
        this.dac = this@AccountInputV1.digit
        this.password = this@AccountInputV1.password
        this.cpf = this@AccountInputV1.cpf
    }
}
