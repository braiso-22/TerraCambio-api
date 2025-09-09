package com.braiso_22.listing.application.port.`in`.addListing

import com.braiso_22.listing.domain.CadastralCode
import com.braiso_22.listing.domain.ListingType
import com.braiso_22.listing.domain.OwnerId


data class AddListingCommand(
    val id: OwnerId,
    val listingName: String,
    val type: ListingType,
    val cadastralCode: CadastralCode
)