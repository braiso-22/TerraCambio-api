package com.braiso_22.listing.application.port.out

import com.braiso_22.listing.domain.vo.CadastralCode
import com.braiso_22.listing.domain.vo.Location

interface LocationProvider {
    suspend fun getByCadastralCode(code: CadastralCode): Location?
}