package br.com.xpapi

import br.com.xpapi.config.ConfigService
import com.google.gson.GsonBuilder
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ro.pippo.controller.Controller
import ro.pippo.controller.ControllerApplication
import ro.pippo.core.Application
import ro.pippo.core.ContentTypeEngine
import ro.pippo.core.HttpConstants
import ro.pippo.core.Pippo

@Configuration
class PippoConfiguration {

    @Bean
    fun startPippo(beanFactory: ListableBeanFactory, configService: ConfigService): Pippo {
        val controllers = beanFactory.getBeansOfType(Controller::class.java).values

        val app = object : ControllerApplication() {
            override fun onInit() {
                registerContentTypeEngine(br.com.xpapi.CustomGsonEngine::class.java)

                router.ignorePaths("/favicon.ico")
                controllers.forEach { addControllers(it) }
            }
        }

        injectRequiredConfig(configService, app, "application.name")
        injectRequiredConfig(configService, app, "application.version")
        injectRequiredConfig(configService, app, "application.languages")
        injectRequiredConfig(configService, app, "application.name")

        injectRequiredConfig(configService, app, "server.port")
        injectRequiredConfig(configService, app, "server.host")
        injectRequiredConfig(configService, app, "server.contextPath")

        injectRequiredConfig(configService, app, "jetty.minThreads")
        injectRequiredConfig(configService, app, "jetty.maxThreads")
        injectRequiredConfig(configService, app, "jetty.idleTimeout")

        val pippo = Pippo(app)
        pippo.start()

        return pippo
    }

    private fun injectRequiredConfig(configService: ConfigService, app: ControllerApplication, property: String) {
        app.pippoSettings.overrideSetting(property, configService.getRequiredString(property))
    }
}

class CustomGsonEngine : ContentTypeEngine {

    private val gson = GsonBuilder()
            .serializeNulls()
            .create()

    override fun init(application: Application) {}

    override fun getContentType() = HttpConstants.ContentType.APPLICATION_JSON

    override fun toString(value: Any) = gson.toJson(value)!!

    override fun <T> fromString(content: String, classOfT: Class<T>) = gson.fromJson(content, classOfT)!!

}
