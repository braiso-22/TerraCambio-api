package com.braiso_22.listing.application.port.`in`.addListing

import com.braiso_22.listing.domain.vo.CadastralCode
import com.braiso_22.listing.domain.vo.ListingName
import com.braiso_22.listing.domain.vo.ListingType
import com.braiso_22.listing.domain.vo.OwnerId


data class AddListingCommand(
    val id: OwnerId,
    val listingName: ListingName,
    val type: ListingType,
    val cadastralCode: CadastralCode
)