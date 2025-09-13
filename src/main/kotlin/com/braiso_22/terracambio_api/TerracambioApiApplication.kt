package com.braiso_22.terracambio_api

import listing.intraestructure.adapters.`in`.springboot_api.configuration.di.beans
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan("listing")
@SpringBootApplication
class TerracambioApiApplication

fun main(args: Array<String>) {
    runApplication<TerracambioApiApplication>(*args) {
        addInitializers(beans())
    }
}
