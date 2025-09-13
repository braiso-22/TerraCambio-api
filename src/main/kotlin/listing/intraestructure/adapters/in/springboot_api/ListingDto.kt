package listing.intraestructure.adapters.`in`.springboot_api

import kotlin.uuid.ExperimentalUuidApi

data class ListingDto @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String,
    val listingName: String,
    val transactions: List<TransactionDto>,
    val cadastralCode: String
)

data class TransactionDto(
    val type: String,
    val value: Double
)
