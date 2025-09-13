package listing.domain

import com.braiso_22.listing.domain.vo.*
import listing.domain.vo.ListingId
import listing.domain.vo.ListingName
import listing.domain.vo.OwnerId
import kotlin.uuid.ExperimentalUuidApi

data class Listing @OptIn(ExperimentalUuidApi::class) constructor(
    val id: ListingId,
    val name: ListingName,
    val listingTransactions: ListingTransactions,
    val location: Location,
    val ownerId: OwnerId
)
