package listing.intraestructure.adapters.`in`.springboot_api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@RestController
@RequestMapping("/api/v1/listing")
class ListingController {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @OptIn(ExperimentalUuidApi::class)
    @GetMapping
    fun getAllListings(): List<ListingDto> {
        logger.info("ListingController::getAllListings")
        return listOf(
            ListingDto(
                id = Uuid.random().toHexString(),
                listingName = "Tracy Robinson",
                transactions = listOf(
                    TransactionDto("BUY", 1000.0),
                ),
                cadastralCode = "at"
            )
        )
    }
}