package br.com.xpapi.mobileapi.dto

data class RequestData(val account: Account)

class Account {
    var agency: String = ""
    var account: String = ""
    var dac: String = ""
    var password: String = ""
    var cpf: String = ""

    fun fullAccount() = account + dac
}

class LoginResponse(
        var cpf: String = "",
        val account: String = "",
        val branchAccount: BranchAccount,
        val users: List<String>
)

class UserData(
        val status: Status = Status.SUCCESS,
        var cpf: String = "",
        var branchAccount: BranchAccount = BranchAccount("", "", ""),
        var users: List<String> = emptyList()
) {
    var investments: Investments = Investments()
}

class Investments {
    var investmentFunds: List<InvestmentFund> = emptyList()

    class InvestmentFund(
            val name: String,
            val dischargeNetBalance: Double,
            val shareBalance: Double,
            val netBalance: Double,
            val grossBalance: Double
    )

//    lateinit var stocks: List<Stocks>
//    lateinit var bonds: List<Bonds>
}

class BranchAccount(
        val branch: String,
        val account: String,
        val digit: String
)

enum class Status {
    SUCCESS, ERROR
}