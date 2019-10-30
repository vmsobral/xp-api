package br.com.xpapi.mobileapi.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Positions(
        @SerializedName("positions")
        @Expose
        var positions: List<Position>,
        @SerializedName("summary")
        @Expose
        var summary: Summary
)

data class Position(
        @SerializedName("items")
        @Expose
        var items: List<Item>,
        @SerializedName("name")
        @Expose
        var name: String,
        @SerializedName("balance")
        @Expose
        var balance: Double,
        @SerializedName("percent")
        @Expose
        var percent: Double,
        @SerializedName("investedValue")
        @Expose
        var investedValue: Double,
        @SerializedName("productType")
        @Expose
        var productType: Int,
        @SerializedName("endpoint")
        @Expose
        var endpoint: Endpoint
)

data class Summary(
        @SerializedName("netWorth")
        @Expose
        var netWorth: Double,
        @SerializedName("investedValue")
        @Expose
        var investedValue: Double,
        @SerializedName("balance")
        @Expose
        var balance: Double,
        @SerializedName("forecast")
        @Expose
        var forecast: Double
)

data class Item(
        @SerializedName("title")
        @Expose
        var title: String,
        @SerializedName("nickname")
        @Expose
        var nickname: String,
        @SerializedName("amount")
        @Expose
        var amount: Double,
        @SerializedName("additionalData")
        @Expose
        var additionalData: List<List<String>>,
        @SerializedName("id")
        @Expose
        var id: String,
        @SerializedName("actions")
        @Expose
        var actions: List<Action>
)

data class Action(
        @SerializedName("title")
        @Expose
        var title: String,
        @SerializedName("action")
        @Expose
        var action: Int
)

data class Endpoint(
        @SerializedName("detail")
        @Expose
        var detail: String,
        @SerializedName("investment")
        @Expose
        var investment: String,
        @SerializedName("redemption")
        @Expose
        var redemption: String,
        @SerializedName("orderValidation")
        @Expose
        var orderValidation: String,
        @SerializedName("orderRedemptionValidation")
        @Expose
        var orderRedemptionValidation: String
)
