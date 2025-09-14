package listing.application.port.`in`.validateCadastralCode

import listing.application.port.out.LocationProvider
import listing.domain.vo.CadastralCode

interface ValidateCadastralCode {
    suspend operator fun invoke(command: ValidateCadastralCodeCommand): ValidateCadastralCodeResult
}

fun ValidateCadastralCode(locationProvider: LocationProvider): ValidateCadastralCode {
    return ValidateCadastralCodeUseCase(locationProvider)
}

private class ValidateCadastralCodeUseCase(private val locationProvider: LocationProvider) : ValidateCadastralCode {
    override suspend fun invoke(command: ValidateCadastralCodeCommand): ValidateCadastralCodeResult {
        val code = try {
            CadastralCode(command.code)
        } catch (ex: IllegalArgumentException) {
            return ValidateCadastralCodeResult.InvalidFormat(ex.message ?: "Invalid format")
        }
        locationProvider.getByCadastralCode(
            code
        ) ?: return ValidateCadastralCodeResult.NotFound

        return ValidateCadastralCodeResult.Valid
    }
}