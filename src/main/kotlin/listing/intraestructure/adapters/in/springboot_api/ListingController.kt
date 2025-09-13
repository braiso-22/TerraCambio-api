package listing.intraestructure.adapters.`in`.springboot_api

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
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
    fun getAllListings(): ResponseEntity<List<ListingDto>> {

        logger.info("ListingController::getAllListings")
        val list = listOf(
            ListingDto(
                id = Uuid.random().toHexString(),
                listingName = "Tracy Robinson",
                transactions = listOf(
                    TransactionDto(TransactionType.BUY, 1000),
                ),
                cadastralCode = "at"
            )
        )

        return ResponseEntity.ok(list)
    }
}