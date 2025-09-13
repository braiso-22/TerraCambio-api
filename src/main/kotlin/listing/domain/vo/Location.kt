package com.braiso_22.listing.domain.vo

import listing.domain.vo.GeoLocation

data class Location(
    val geoLocation: GeoLocation,
    val name: String,
) {
    init {
        require(name.isNotBlank()) { "Location name must not be blank" }
    }
}