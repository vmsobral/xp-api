package br.com.xpapi.mobileapi.dto

data class XPAccount(
        var brand: Int,
        var branch: String,
        var account: String,
        var name: String,
        var entityType: Int
)

data class KeyboardOption(
        var id: String,
        var options: List<Int>
)

data class KeyboardKeys(
        var keys: List<String>
)