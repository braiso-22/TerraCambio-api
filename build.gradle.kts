plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.spring.framework.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "com.braiso_22"
version = "0.0.1-SNAPSHOT"
description = "Api for terracambio project"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(libs.springboot.starter.web)

    // for coroutines and flows
    implementation(libs.springboot.starter.webflux)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.reactor)

    // serialization
    implementation(libs.jackson.module.kotlin)
    implementation(libs.kotlin.reflect)

    // swagger-ui and openapi
    implementation(libs.spring.doc.openapi)


    // testing
    developmentOnly(libs.springboot.dev.tools)
    testImplementation(libs.kotlin.test.junit)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}
