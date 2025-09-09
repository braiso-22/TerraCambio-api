package com.braiso_22.listing.application.port.out

import com.braiso_22.listing.domain.Listing
import com.braiso_22.listing.domain.OwnerId

interface ListingRepository {
    suspend fun add(listing: Listing)
    suspend fun getByOwnerId(id: OwnerId): List<Listing>
    suspend fun getAll(): List<Listing>
}