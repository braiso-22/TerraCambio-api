package listing.intraestructure.configuration.di

import io.ktor.client.*
import listing.application.port.`in`.addListing.AddListing
import listing.application.port.`in`.getListings.GetListings
import listing.application.port.`in`.validateCadastralCode.ValidateCadastralCode
import listing.application.port.out.ListingRepository
import listing.application.port.out.LocationProvider
import listing.intraestructure.adapters.out.httpClient
import listing.intraestructure.adapters.out.providers.FakeLocationProvider
import listing.intraestructure.adapters.out.repository.InMemoryListingRepository
import org.springframework.context.support.beans

fun beans() = beans {
    bean<HttpClient> {
        httpClient
    }
    bean<LocationProvider> { FakeLocationProvider() }
    bean<ListingRepository> { InMemoryListingRepository() }
    bean<GetListings> {
        GetListings(listingRepository = ref())
    }
    bean<ValidateCadastralCode> {
        ValidateCadastralCode(
            locationProvider = ref()
        )
    }

    bean<AddListing> {
        AddListing(
            listingRepository = ref(),
            locationProvider = ref()
        )
    }
}