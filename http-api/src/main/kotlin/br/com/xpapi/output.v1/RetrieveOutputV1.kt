package br.com.xpapi.output.v1

import br.com.xpapi.mobileapi.dto.Investments
import br.com.xpapi.mobileapi.dto.UserData
import br.com.xpapi.output.v1.common.AgencyAndAccountOutputV1
import br.com.xpapi.output.v1.common.RequestStatusOutputV1
import br.com.xpapi.output.v1.common.toOutputV1
import com.google.gson.annotations.SerializedName

data class RetrieveOutputV1(
        val status: RequestStatusOutputV1,
        @SerializedName("investment") val investment: InvestmentsOutputV1?,
        val users: List<String>?,
        val cpf: String?,
        val agencyAndAccount: AgencyAndAccountOutputV1?
)

data class InvoiceOutputV1(
        val dueDate: String?,
        val closingDate: String?,
        val transactions: List<CreditCardTransactionOutputV1>?,
        val total: String?
)

data class InvestmentsOutputV1(
//        @SerializedName("savingAccountsData") val savingAccounts: List<SavingAccountOutputV1>?,
        @SerializedName("investmentFund") val investmentFunds: List<InvestmentFundOutputV1>
//        @SerializedName("privatePension") val privatePensions: List<PrivatePensionOutputV1>?,
//        @SerializedName("investmentCDB") val cdbInvestments: List<InvestmentCdbOutputV1>?
)

data class InvestmentFundOutputV1(
        val name: String?,
        val dischargeNetBalance: Double,
        val shareBalance: Double,
        val grossBalance: Double,
        val netBalance: Double
)

data class CreditCardTransactionOutputV1(
        val date: String?,
        val description: String?,
        val value: String?,
        val categoryId: Long?,
        val docto: String?,
        val currency: String?
)

fun UserData.toOutputV1() = RetrieveOutputV1(
        status = this.status.toOutputV1(),
        investment = this.investments.toOutputV1(),
        users = this.users,
        cpf = this.cpf,
        agencyAndAccount = this.branchAccount.toOutputV1()
)

private fun Investments.toOutputV1() = InvestmentsOutputV1(
        investmentFunds = this.investmentFunds.map { investmentFund ->
            investmentFund.toOutputV1() }
)

private fun Investments.InvestmentFund.toOutputV1() = InvestmentFundOutputV1(
        name = this.name,
        dischargeNetBalance = this.dischargeNetBalance,
        shareBalance = this.shareBalance,
        grossBalance = this.grossBalance,
        netBalance = this.netBalance
)