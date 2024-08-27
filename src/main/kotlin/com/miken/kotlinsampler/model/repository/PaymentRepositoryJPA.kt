package com.miken.kotlinsampler.model.repository

import com.miken.kotlinsampler.model.Payment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PaymentRepositoryJPA : JpaRepository<Payment, Int> {

    fun findByGuid(id: UUID?): Payment?
}