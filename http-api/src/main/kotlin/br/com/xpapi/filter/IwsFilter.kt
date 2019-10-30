package br.com.xpapi.filter

import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import ro.pippo.core.Application

object IwsFilter {

    private val gson = Gson()
    private val log = LoggerFactory.getLogger(br.com.xpapi.filter.IwsFilter::class.java)

    fun setup(application: Application) {

        application.ANY("/.") {
            br.com.xpapi.filter.IwsFilter.log.info("Finishing Request in Iws")
            MDC.clear()
        }.runAsFinally()
    }

}