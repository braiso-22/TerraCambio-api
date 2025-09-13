package listing.domain.vo

@JvmInline
value class Money(val cents: Long) {
    init {
        require(cents >= 0) { "Money must be greater than or equal to 0.0" }
    }

    fun toDecimal(): Double = cents.toDouble() / 100.0
}