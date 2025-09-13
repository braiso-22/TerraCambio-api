package listing.application.port.`in`.addListing

import listing.application.port.`in`.CommandTransaction

data class AddListingCommand(
    val ownerId: String,
    val listingName: String,
    val types: List<CommandTransaction>,
    val cadastralCode: String
)
