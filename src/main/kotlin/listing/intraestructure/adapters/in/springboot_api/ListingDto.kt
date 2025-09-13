package listing.intraestructure.adapters.`in`.springboot_api

import io.swagger.v3.oas.annotations.media.Schema
import kotlin.uuid.ExperimentalUuidApi

data class ListingDto @OptIn(ExperimentalUuidApi::class) constructor(
    @param:Schema(format = "uuid")
    val id: String,
    val listingName: String,
    val transactions: List<TransactionDto>,
    val cadastralCode: String
)

enum class TransactionType {
    BUY, RENT, SWITCH
}

data class TransactionDto(
    val type: TransactionType,
    val cents: Long?
)
