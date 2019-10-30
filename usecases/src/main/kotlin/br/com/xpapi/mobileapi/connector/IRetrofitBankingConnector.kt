package br.com.xpapi.mobileapi.connector

import br.com.xpapi.mobileapi.dto.KeyboardOption
import br.com.xpapi.mobileapi.dto.KeyboardKeys
import br.com.xpapi.mobileapi.dto.XPAccount
import br.com.xpapi.mobileapi.dto.Positions
import retrofit2.Call
import retrofit2.http.*

interface IRetrofitBankingConnector {

    /** LOGIN **/
    @GET("client/security/accounts/XP/{account}")
    fun accounts(@Path("account") account: String): Call<XPAccount>

    @GET("client/security/accesstokens/keyboard")
    fun keyboard(@Header("Authorization") authorization: String): Call<List<KeyboardOption>>

    @POST("client/security/authenticate")
    fun authenticate(@Body keyboardKeys: KeyboardKeys, @Header("Authorization") authorization: String): Call<Void>

    /** CLIENT INFO **/
    @GET("my-portfolio/v2/profile")
    fun profile(@Header("Authorization") authorization: String): Call<Positions>

    @GET("yield-mobile/v1/customers/positions")
    fun clientInfo(@Header("Authorization") authorization: String): Call<Positions>
                                                
    /** INVESTMENTS **/
    @GET("yield-mobile/v1/customers/positions")
    fun positions(@Header("Authorization") authorization: String): Call<Positions>
}
