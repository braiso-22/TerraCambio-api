package com.braiso_22.listing.application.port.`in`.addListing

import com.braiso_22.listing.domain.vo.Money
import com.braiso_22.listing.domain.vo.TransactionType

data class AddListingCommand constructor(
    val id: String,
    val listingName: String,
    val types: List<CommandListingTransaction>,
    val cadastralCode: String
)

data class CommandListingTransaction(
    val type: CommandTransactionType,
    val value: Long? = null
) {
    fun toListingType(): TransactionType {
        return when (type) {
            CommandTransactionType.BUY -> {
                require(value != null) {
                    "Value must be provided for BUY listing type"
                }
                TransactionType.Buy(Money(value))
            }

            CommandTransactionType.RENT -> {
                require(value != null) {
                    "Value must be provided for RENT listing type"
                }
                TransactionType.Rent(Money(value))
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

