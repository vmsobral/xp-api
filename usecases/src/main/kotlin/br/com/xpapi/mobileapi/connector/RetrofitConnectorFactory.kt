package br.com.xpapi.mobileapi.connector

import br.com.xpapi.config.ConfigService
import okhttp3.OkHttpClient
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

@Component
class RetrofitConnectorFactory(private val configService: ConfigService) {

    companion object {
        private const val PAGE_LOAD_TIMEOUT = 15L
    }

    fun create(): XPRetrofitConnector {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)

//        if (defaultProxy != null) okHttpClientBuilder.proxy(convertDefaultToProxy(defaultProxy))

        okHttpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header("Ocp-Apim-Subscription-Key", "3508e3a662fd4528b6097924f18dd3d0")
                    .method(original.method(), original.body())
                    .build()

            try {
                return@addInterceptor chain.proceed(request)
            } catch (e: IOException) {
                throw RuntimeException("Unable to connect to XP", e)
            }
        }

        val okHttpClient = okHttpClientBuilder.build()
        val connector = Retrofit.Builder()
                .baseUrl(configService.getRequiredString("xp.baseurl"))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IRetrofitBankingConnector::class.java)
        return XPRetrofitConnector(connector, "")
    }

//    private fun convertDefaultToProxy(defaultProxy: DefaultProxy?): Proxy? {
//        if (defaultProxy == null) {
//            throw IllegalArgumentException("A valid Proxy is required")
//        }
//        if (defaultProxy.isAuthenticated) {
//            val authenticator = object : Authenticator() {
//
//                override fun getPasswordAuthentication(): PasswordAuthentication {
//                    return PasswordAuthentication(defaultProxy.username,
//                            defaultProxy.password.toCharArray())
//                }
//            }
//            Authenticator.setDefault(authenticator)
//        }
//        val socketAddress = InetSocketAddress(defaultProxy.ip, defaultProxy.port.toInt())
//        return Proxy(Proxy.Type.HTTP, socketAddress)
//    }

}
