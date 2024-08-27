package com.miken.kotlinsampler.model

import com.miken.kotlinsampler.model.repository.IdentifiedEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter

@Entity
class Payment(

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
        name = "sequence-generator",
        parameters = [Parameter(name = "sequence_name", value = "payment_seq"), Parameter(
            name = "initial_value",
            value = "1"
        ), Parameter(name = "increment_size", value = "1")]
    )
    @Column(name = "payment_id")
    override var id: Int? = null,
    var bic: String? = null,
    var iban: String? = null,
    var currency: String? = null,
    var amount: Int? = null,
) : IdentifiedEntity {

    override fun toString(): String {
        return "Payment(id=$id, bic=$bic, iban=$iban, currency=$currency, amount=$amount)"
    }

}

