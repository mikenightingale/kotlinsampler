package com.miken.kotlinsampler.adaptor.resource

import com.miken.kotlinsampler.model.Payment
import java.util.*


data class PaymentResource(
    var id: Int? = null,
    val bic: String,
    val iban: String,
    val currency: String,
    val amount: Int?,
    val guid: UUID = UUID.randomUUID()
) {
    companion object {
        fun fromEntity(e: Payment): PaymentResource {
            return PaymentResource(
                id = e.id,
                bic = e.bic!!,
                iban = e.iban!!,
                currency = e.currency!!,
                amount = e.amount!!,
                guid = e.guid!!
            )
        }

        fun fromResource(e: PaymentResource): Payment {
            return Payment(id = e.id, bic = e.bic, iban = e.iban, currency = e.currency, amount = e.amount, guid = e.guid)
        }
    }
}
