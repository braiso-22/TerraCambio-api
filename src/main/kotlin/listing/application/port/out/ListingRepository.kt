package listing.application.port.out

import listing.domain.Listing
import listing.domain.vo.OwnerId

interface ListingRepository {
    suspend fun add(listing: Listing)
    suspend fun getByOwnerId(id: OwnerId): List<Listing>
    suspend fun getAll(): List<Listing>
}