package com.braiso_22.listing.application.port.`in`.addListing

sealed interface AddListingResult {
    data object Success : AddListingResult
    sealed interface Failure : AddListingResult
    data class InvalidCommand(val message: String) : Failure
    data class CadastralCodeNotFound(val message: String) : Failure
}