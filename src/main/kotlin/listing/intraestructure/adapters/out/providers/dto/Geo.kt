package listing.intraestructure.adapters.out.providers.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    @SerialName("srs")
    val srs: String,
    @SerialName("xcen")
    val xcen: String,
    @SerialName("ycen")
    val ycen: String
)