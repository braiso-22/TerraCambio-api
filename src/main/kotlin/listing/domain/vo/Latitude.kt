package listing.domain.vo


@JvmInline
value class Latitude(val value: Double) {
    init {
        require(value.isFinite()) { "Latitude must be a finite number" }
        require(value in -90.0..90.0) { "Latitude must be in range [-90.0, 90.0]" }
    }
}