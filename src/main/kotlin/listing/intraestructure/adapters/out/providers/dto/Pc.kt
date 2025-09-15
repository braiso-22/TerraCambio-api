package listing.intraestructure.adapters.out.providers.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pc(
    @SerialName("pc1")
    val pc1: String,
    @SerialName("pc2")
    val pc2: String
)