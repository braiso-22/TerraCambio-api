package com.braiso_22.listing.domain.vo

sealed interface ListingType {
    data class Buy(val value: Money) : ListingType
    data class Rent(val value: Money) : ListingType
    data object Switch : ListingType
}