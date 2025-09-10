package com.braiso_22.listing.domain

import com.braiso_22.listing.domain.vo.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ListingDomainValidationTest {

    @Test
    fun `money allows zero and positive values`() {
        // zero
        Money(0)
        // positive
        Money(1500)
    }

    @Test
    fun `money rejects negative values`() {
        assertFailsWith<IllegalArgumentException> { Money(-1) }
    }

    @Test
    fun `latitude accepts bounds and middle`() {
        Latitude(-90.0)
        Latitude(0.0)
        Latitude(90.0)
    }

    @Test
    fun `latitude rejects out of range and non-finite`() {
        assertFailsWith<IllegalArgumentException> { Latitude(Double.NaN) }
        assertFailsWith<IllegalArgumentException> { Latitude(Double.POSITIVE_INFINITY) }
        assertFailsWith<IllegalArgumentException> { Latitude(Double.NEGATIVE_INFINITY) }
        assertFailsWith<IllegalArgumentException> { Latitude(-90.0000001) }
        assertFailsWith<IllegalArgumentException> { Latitude(90.0000001) }
    }

    @Test
    fun `longitude accepts bounds and middle`() {
        Longitude(-180.0)
        Longitude(0.0)
        Longitude(180.0)
    }

    @Test
    fun `longitude rejects out of range and non-finite`() {
        assertFailsWith<IllegalArgumentException> { Longitude(Double.NaN) }
        assertFailsWith<IllegalArgumentException> { Longitude(Double.POSITIVE_INFINITY) }
        assertFailsWith<IllegalArgumentException> { Longitude(Double.NEGATIVE_INFINITY) }
        assertFailsWith<IllegalArgumentException> { Longitude(-180.0000001) }
        assertFailsWith<IllegalArgumentException> { Longitude(180.0000001) }
    }

    @Test
    fun `geolocation accepts valid lat lon`() {
        GeoLocation(Latitude(12.34), Longitude(56.78))
    }

    @Test
    fun `location requires non-blank name`() {
        Location(geoLocation = GeoLocation(Latitude(0.0), Longitude(0.0)), name = "A place")
        assertFailsWith<IllegalArgumentException> {
            Location(geoLocation = GeoLocation(Latitude(0.0), Longitude(0.0)), name = " ")
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `listing requires non-blank name and at least one type`() {
        val id = ListingId(Uuid.random())
        val name = ListingName("Nice lising")
        val owner = OwnerId(Uuid.random())
        val loc = Location(GeoLocation(Latitude(0.0), Longitude(0.0)), name = "Earth")
        // valid
        Listing(
            id = id,
            name = name,
            listingTypes = setOf(ListingType.Switch),
            geolocation = loc,
            ownerId = owner
        )

        // blank name
        assertFailsWith<IllegalArgumentException> {
            Listing(
                id = id,
                name = ListingName("\t\n  "),
                listingTypes = setOf(ListingType.Switch),
                geolocation = loc,
                ownerId = owner
            )
        }
        // no types
        assertFailsWith<IllegalArgumentException> {
            Listing(id = id, name = name, listingTypes = emptySet(), geolocation = loc, ownerId = owner)
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    @Test
    fun `listing buy and rent accept non-negative money`() {
        val id = ListingId(Uuid.random())
        val name = ListingName("With price")
        val owner = OwnerId(Uuid.random())
        val loc = Location(GeoLocation(Latitude(0.0), Longitude(0.0)), name = "Earth")
        val buy = ListingType.Buy(Money(1999))
        val rent = ListingType.Rent(Money(0))
        val l =
            Listing(id = id, name = name, listingTypes = setOf(buy, rent), geolocation = loc, ownerId = owner)
        assertEquals(2, l.listingTypes.size)
    }

    @Test
    fun `money toDecimal converts cents to decimal correctly`() {
        assertEquals(0.0, Money(0).toDecimal(), 1e-9)
        assertEquals(0.01, Money(1).toDecimal(), 1e-9)
        assertEquals(19.99, Money(1999).toDecimal(), 1e-9)
        assertEquals(2.0, Money(200).toDecimal(), 1e-9)
    }

    @Test
    fun `money toDecimal handles larger values`() {
        assertEquals(1234567.89, Money(123456789).toDecimal(), 1e-9)
    }
}
