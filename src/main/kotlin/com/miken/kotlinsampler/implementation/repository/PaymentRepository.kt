package com.miken.kotlinsampler.implementation.repository

import com.miken.kotlinsampler.model.Payment
import com.miken.kotlinsampler.model.repository.GenericRepository
import com.miken.kotlinsampler.model.repository.PaymentRepositoryJPA
import org.springframework.stereotype.Component
import java.util.*

@Component
class PaymentRepository(override val innerRepos: PaymentRepositoryJPA) : GenericRepository<Payment, Int>{

    fun findByGuid(id: UUID): Payment? {
        return innerRepos.findByGuid(id)
    }
}