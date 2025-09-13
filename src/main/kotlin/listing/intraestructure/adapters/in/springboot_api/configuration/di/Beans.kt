package listing.intraestructure.adapters.`in`.springboot_api.configuration.di

import listing.application.port.`in`.addListing.AddListing
import listing.application.port.`in`.getListings.GetListings
import listing.application.port.out.ListingRepository
import listing.application.port.out.LocationProvider
import listing.intraestructure.adapters.out.providers.FakeLocationProvider
import listing.intraestructure.adapters.out.repository.InMemoryListingRepository
import org.springframework.context.support.beans

fun beans() = beans {
    bean<LocationProvider> { FakeLocationProvider() }
    bean<ListingRepository> { InMemoryListingRepository() }
    bean<GetListings> {
        GetListings(listingRepository = ref())
    }
    bean<AddListing> {
        AddListing(
            listingRepository = ref(),
            locationProvider = ref()
        )
    }
}