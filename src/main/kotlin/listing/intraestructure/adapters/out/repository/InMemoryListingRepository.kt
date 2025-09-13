package listing.intraestructure.adapters.out.repository

import listing.application.port.out.ListingRepository
import listing.domain.Listing
import listing.domain.vo.OwnerId

class InMemoryListingRepository : ListingRepository {

    private val listings = mutableListOf<Listing>()

    override suspend fun add(listing: Listing) {
        listings.add(listing)
    }

    override suspend fun getByOwnerId(id: OwnerId): List<Listing> {
        return listings.filter { it.ownerId == id }
    }

    override suspend fun getAll(): List<Listing> {
        return listings.toList()
    }
}