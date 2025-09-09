package com.braiso_22.listing.domain

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Listing @OptIn(ExperimentalUuidApi::class) constructor(
    val id: ListingId,
    val name: String,
    val listingTypes: Set<ListingType>,
    val geolocation: Location,
    val ownerId: OwnerId
) {
    init {
        require(name.isNotBlank()) { "Listing name must not be blank" }
        require(listingTypes.isNotEmpty()) { "Listing must have at least one listing type" }
    }
}

@JvmInline
value class ListingId @OptIn(ExperimentalUuidApi::class) constructor(val value: Uuid)

@JvmInline
value class OwnerId @OptIn(ExperimentalUuidApi::class) constructor(val value: Uuid)

sealed interface ListingType {
    data class Buy(val value: Money) : ListingType
    data class Rent(val value: Money) : ListingType
    data object Switch : ListingType
}

@JvmInline
value class Money(val cents: Long) {
    init {
        require(cents >= 0) { "Money must be greater than or equal to 0.0" }
    }

    fun toDecimal(): Double = cents.toDouble() / 100.0
}

data class Location(
    val geoLocation: GeoLocation,
    val name: String,
) {
    init {
        require(name.isNotBlank()) { "Location name must not be blank" }
    }
}

data class GeoLocation(
    val latitude: Latitude,
    val longitude: Longitude,
)

@JvmInline
value class Latitude(val value: Double) {
    init {
        require(value.isFinite()) { "Latitude must be a finite number" }
        require(value in -90.0..90.0) { "Latitude must be in range [-90.0, 90.0]" }
    }
}

@JvmInline
value class Longitude(val value: Double) {
    init {
        require(value.isFinite()) { "Longitude must be a finite number" }
        require(value in -180.0..180.0) { "Longitude must be in range [-180.0, 180.0]" }
    }
}

@JvmInline
value class CadastralCode(val value: String) {
    init {
        require(value.length == 14) { "Cadastral code length must be of 14" }
    }
}