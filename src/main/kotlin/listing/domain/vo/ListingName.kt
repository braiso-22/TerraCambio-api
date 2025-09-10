package com.braiso_22.listing.domain.vo

@JvmInline
value class ListingName(val value: String) {
    init {
        require(value.isNotBlank()) { "Listing name can't be empty" }
    }
}