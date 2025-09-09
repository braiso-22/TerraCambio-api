package com.braiso_22.listing.domain

data class Listing(
    val id: Int,
    val name: String,
    val listingTypes: List<ListingType>
)


sealed interface ListingType {
    data class Buy(val value: Money) : ListingType
    data class Rent(val value: Money) : ListingType
    data object Switch : ListingType
}

@JvmInline
value class Money(val value: Double)