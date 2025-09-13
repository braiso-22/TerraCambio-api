package listing.intraestructure.adapters.out.providers

import listing.application.port.out.LocationProvider
import listing.domain.vo.*
import kotlin.random.Random

class FakeLocationProvider(
    private val activated: Boolean = true
) : LocationProvider {
    override suspend fun getByCadastralCode(code: CadastralCode): Location? {
        return if (!activated) {
            null
        } else {
            val randomGenerator = Random(code.value.hashCode())

            Location(
                name = "Fake location-$code",
                cadastralCode = code,
                geoLocation = GeoLocation(
                    latitude = Latitude(
                        value = randomGenerator.nextDouble(-90.0, 90.0)
                    ),
                    longitude = Longitude(
                        value = randomGenerator.nextDouble(-180.0,180.0)
                    )
                )
            )
        }
    }
}