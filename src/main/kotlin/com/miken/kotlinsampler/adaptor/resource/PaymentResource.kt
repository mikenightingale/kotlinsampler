package com.miken.kotlinsampler.adaptor.resource

import com.miken.kotlinsampler.model.Payment


data class PaymentResource(
    var id: Int? = null,
    val bic: String,
    val iban: String,
    val currency: String,
    val amount: Int?
) {
    companion object {
        fun fromEntity(e: Payment): PaymentResource {
            return PaymentResource(
                id = e.id,
                bic = e.bic!!,
                iban = e.iban!!,
                currency = e.currency!!,
                amount = e.amount!!
            )
        }

        fun fromResource(e: PaymentResource): Payment {
            return Payment(id = e.id, bic = e.bic, iban = e.iban, currency = e.currency, amount = e.amount)
        }
    }
}
