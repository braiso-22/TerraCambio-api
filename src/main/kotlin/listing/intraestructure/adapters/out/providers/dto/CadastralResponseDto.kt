package listing.intraestructure.adapters.out.providers.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import listing.domain.vo.CadastralCode
import listing.domain.vo.GeoLocation
import listing.domain.vo.Latitude
import listing.domain.vo.Location
import listing.domain.vo.Longitude

@Serializable
data class CadastralResponseDto(
    @SerialName("Consulta_CPMRCResult")
    val topLevelObject: TopLevelObject
) {
    fun toDomain(cadastralCode: CadastralCode): Location? {
        try {
            val coordinates = this.topLevelObject.getCoordinates()

            val name = coordinates.fullName

            val latitudeValue = coordinates.getLatitude()
            val longitudeValue = coordinates.getLongitude()

            return Location(
                name = name,
                cadastralCode = cadastralCode,
                geoLocation = GeoLocation(
                    latitude = Latitude(value = latitudeValue),
                    longitude = Longitude(value = longitudeValue)
                )
            )
        } catch (_: Exception) {
            return null
        }
    }
}