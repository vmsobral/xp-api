package br.com.xpapi

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["br.com.guiabolso"])
class Boot {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            AnnotationConfigApplicationContext(br.com.xpapi.Boot::class.java)
        }
    }

}