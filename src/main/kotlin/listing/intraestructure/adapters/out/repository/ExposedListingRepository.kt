package listing.intraestructure.adapters.out.repository

import listing.application.port.out.ListingRepository
import listing.domain.Listing
import listing.domain.vo.OwnerId

class ExposedListingRepository : ListingRepository {
    override suspend fun add(listing: Listing) {
        TODO("Not yet implemented")
    }

    override suspend fun getByOwnerId(id: OwnerId): List<Listing> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Listing> {
        TODO("Not yet implemented")
    }
}