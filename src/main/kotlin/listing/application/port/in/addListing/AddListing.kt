package com.braiso_22.listing.application.port.`in`.addListing

import com.braiso_22.listing.application.port.out.GeoLocationProvider
import com.braiso_22.listing.application.port.out.ListingRepository
import com.braiso_22.listing.domain.Listing
import com.braiso_22.listing.domain.vo.ListingId
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface AddListing {
    suspend operator fun invoke(command: AddListingCommand): AddListingResult
}

fun AddListing(
    listingRepository: ListingRepository,
    geoLocationProvider: GeoLocationProvider
): AddListing {
    return AddListingUseCase(
        listingRepository,
        geoLocationProvider
    )
}

internal class AddListingUseCase(
    private val listingRepository: ListingRepository,
    private val geoLocationProvider: GeoLocationProvider,
) : AddListing {
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun invoke(command: AddListingCommand): AddListingResult {

        val location = geoLocationProvider.getByCadastralCode(command.cadastralCode)
            ?: return AddListingResult.CadastralCodeNotFound("Cadastral code not found")

        val newId = ListingId(Uuid.random())

        val listing = Listing(
            id = newId,
            name = command.listingName,
            listingTypes = command.types,
            location = location,
            ownerId = command.id
        )
        listingRepository.add(listing)

        return AddListingResult.Success
    }
}