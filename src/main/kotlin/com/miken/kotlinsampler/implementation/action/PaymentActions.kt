package com.miken.kotlinsampler.implementation.action

import com.miken.kotlinsampler.implementation.repository.PaymentRepository
import com.miken.kotlinsampler.model.Payment
import org.springframework.stereotype.Component


@Component
class PaymentActions(private val repos: PaymentRepository) {

    fun listAll(): List<Payment> {
        return repos.findAll();
    }
}