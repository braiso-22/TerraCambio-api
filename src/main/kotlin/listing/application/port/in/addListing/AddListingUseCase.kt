package com.braiso_22.listing.application.port.`in`.addListing

interface AddListingUseCase {
    suspend operator fun invoke(command: AddListingCommand): AddListingResult
}
