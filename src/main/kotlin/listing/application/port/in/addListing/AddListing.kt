package com.braiso_22.listing.application.port.`in`.addListing

import com.braiso_22.listing.application.port.out.LocationProvider
import com.braiso_22.listing.application.port.out.ListingRepository
import com.braiso_22.listing.domain.Listing
import com.braiso_22.listing.domain.vo.CadastralCode
import com.braiso_22.listing.domain.vo.ListingId
import com.braiso_22.listing.domain.vo.ListingName
import com.braiso_22.listing.domain.vo.ListingTransactions
import com.braiso_22.listing.domain.vo.OwnerId
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface AddListing {
    suspend operator fun invoke(command: AddListingCommand): AddListingResult
}

fun AddListing(
    listingRepository: ListingRepository,
    locationProvider: LocationProvider
): AddListing {
    return AddListingUseCase(
        listingRepository,
        locationProvider
    )
}

private class AddListingUseCase(
    private val listingRepository: ListingRepository,
    private val locationProvider: LocationProvider,
) : AddListing {
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun invoke(command: AddListingCommand): AddListingResult {

        val cadastralCode = try {
            CadastralCode(command.cadastralCode)
        } catch (e: IllegalArgumentException) {
            return AddListingResult.BadCommand(e.message ?: "Invalid cadastral code")
        }

        val location = locationProvider.getByCadastralCode(cadastralCode)
            ?: return AddListingResult.CadastralCodeNotFound("Cadastral code not found")

        val listing = try {
            Listing(
                id = ListingId(Uuid.random()),
                name = ListingName(command.listingName),
                listingTransactions = ListingTransactions(command.types.map { it.toListingType() }.toSet()),
                location = location,
                ownerId = OwnerId(Uuid.parse(command.id))
            )
        } catch (e: IllegalArgumentException) {
            return AddListingResult.BadCommand(e.message ?: "Invalid command parameters")
        }

        listingRepository.add(listing)

        return AddListingResult.Success
    }
}