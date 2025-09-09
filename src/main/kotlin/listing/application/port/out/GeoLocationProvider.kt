package com.braiso_22.listing.application.port.out

import com.braiso_22.listing.domain.CadastralCode
import com.braiso_22.listing.domain.GeoLocation

interface GeoLocationProvider {
    suspend fun getByCadastralCode(code: CadastralCode): GeoLocation?
}