package com.braiso_22.listing.domain.vo

sealed interface TransactionType {
    data class Buy(val value: Money) : TransactionType
    data class Rent(val value: Money) : TransactionType
    data object Switch : TransactionType
}

@JvmInline
value class ListingTransactions(val values: Set<TransactionType>) {
    init {
        require(values.isNotEmpty()) { "Listing must have at least one listing type" }
    }
}