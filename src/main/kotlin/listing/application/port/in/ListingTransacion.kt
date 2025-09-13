package listing.application.port.`in`

import listing.domain.vo.TransactionType
import listing.domain.vo.Money

data class CommandTransaction(
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

