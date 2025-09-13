package listing.application.port.`in`.addListing

import listing.domain.vo.Money
import com.braiso_22.listing.domain.vo.TransactionType

data class AddListingCommand (
    val id: String,
    val listingName: String,
    val types: List<CommandListingTransaction>,
    val cadastralCode: String
)

data class CommandListingTransaction(
    val type: CommandTransactionType,
    val cents: Long? = null
) {
    fun toListingType(): TransactionType {
        return when (type) {
            CommandTransactionType.BUY -> {
                require(cents != null) {
                    "Cents must be provided for BUY listing type"
                }
                TransactionType.Buy(Money(cents))
            }

            CommandTransactionType.RENT -> {
                require(cents != null) {
                    "Cents must be provided for RENT listing type"
                }
                TransactionType.Rent(Money(cents))
            }

            CommandTransactionType.SWITCH -> {
                TransactionType.Switch
            }
        }
    }
}

enum class CommandTransactionType {
    BUY, RENT, SWITCH
}

