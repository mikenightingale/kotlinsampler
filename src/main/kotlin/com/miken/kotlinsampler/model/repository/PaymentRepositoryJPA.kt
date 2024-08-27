package com.miken.kotlinsampler.model.repository

import com.miken.kotlinsampler.model.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepositoryJPA : JpaRepository<Payment, Int> {
}