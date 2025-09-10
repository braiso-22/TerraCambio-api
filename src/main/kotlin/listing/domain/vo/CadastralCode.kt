package com.braiso_22.listing.domain.vo

@JvmInline
value class CadastralCode(val value: String) {
    init {
        require(value.length == 14) { "Cadastral code length must be of 14" }
    }
}