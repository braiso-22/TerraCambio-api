package listing.intraestructure.adapters.out.providers.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Control(
    @SerialName("cucoor")
    val cucoor: Int
)