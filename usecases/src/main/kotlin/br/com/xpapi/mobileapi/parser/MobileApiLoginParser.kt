package br.com.xpapi.mobileapi.parser

import br.com.xpapi.mobileapi.dto.BranchAccount
import br.com.xpapi.mobileapi.dto.XPAccount
import org.springframework.stereotype.Component

@Component
class MobileApiLoginParser {
    fun parseUsersList(xpAccount: XPAccount): List<String> {
        val usersList = ArrayList<String>()
        usersList.add(xpAccount.name)

        return usersList
    }

//    fun parseCpf(xpAccount: XPAccount) = ""

    fun parseAgencyAccount(xpAccount: XPAccount) = BranchAccount(
            branch = "0001",
            account = xpAccount.account.dropLast(1),
            digit = xpAccount.account.takeLast(1)
    )
}
