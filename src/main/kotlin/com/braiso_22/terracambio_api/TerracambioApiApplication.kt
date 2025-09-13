package com.braiso_22.terracambio_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.support.beans

@ComponentScan("listing")
@SpringBootApplication
class TerracambioApiApplication

fun beans() = beans {
  //  bean<AddListing>()
}

fun main(args: Array<String>) {
    runApplication<TerracambioApiApplication>(*args) {
        addInitializers(beans())
    }
}
