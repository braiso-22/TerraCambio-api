package com.braiso_22.listing.application.port.`in`.addListing

sealed interface AddListingResult {
    data object Success : AddListingResult
    data class CadastralCodeNotFound(val message: String) : AddListingResult
}