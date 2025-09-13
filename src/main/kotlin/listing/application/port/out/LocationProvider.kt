package listing.application.port.out

import listing.domain.vo.CadastralCode
import listing.domain.vo.Location

interface LocationProvider {
    suspend fun getByCadastralCode(code: CadastralCode): Location?
}