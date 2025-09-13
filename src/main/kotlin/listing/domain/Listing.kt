package com.braiso_22.listing.domain

import com.braiso_22.listing.domain.vo.*
import kotlin.uuid.ExperimentalUuidApi

data class Listing @OptIn(ExperimentalUuidApi::class) constructor(
    val id: ListingId,
    val name: ListingName,
    val listingTransactions: ListingTransactions,
    val location: Location,
    val ownerId: OwnerId
)
