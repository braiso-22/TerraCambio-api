package com.braiso_22.listing.domain


import com.braiso_22.listing.domain.vo.*
import kotlin.uuid.ExperimentalUuidApi

data class Listing @OptIn(ExperimentalUuidApi::class) constructor(
    val id: ListingId,
    val name: ListingName,
    val listingTypes: Set<ListingType>,
    val geolocation: Location,
    val ownerId: OwnerId
) {
    init {
        require(listingTypes.isNotEmpty()) { "Listing must have at least one listing type" }
    }
}
