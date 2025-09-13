package listing.application.port.`in`.getListings

import listing.application.port.out.ListingRepository
import listing.domain.Listing

interface GetListings {
    suspend operator fun invoke(): List<Listing>
}

fun GetListings(
    listingRepository: ListingRepository
): GetListings = GetListingsUseCase(listingRepository)

private class GetListingsUseCase(
    private val listingRepository: ListingRepository
) : GetListings {

    override suspend fun invoke(): List<Listing> {
        val listings = listingRepository.getAll()
        return listings
    }
}

