package com.miken.kotlinsampler.implementation.repository

import com.miken.kotlinsampler.model.Payment
import com.miken.kotlinsampler.model.repository.GenericRepository
import com.miken.kotlinsampler.model.repository.PaymentRepositoryJPA
import org.springframework.stereotype.Component

@Component
class PaymentRepository(override val innerRepos: PaymentRepositoryJPA) : GenericRepository<Payment, Int>