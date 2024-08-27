package com.miken.kotlinsampler.implementation.kafka

import com.miken.kotlinsampler.adaptor.resource.PaymentResource
import com.miken.kotlinsampler.implementation.repository.PaymentRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class PaymentConsumer(
    private val repos: PaymentRepository
) {
    @KafkaListener(topics = ["payment"])
    fun listen(p: PaymentResource) {
        logger.info { p.toString() }
        repos.save(PaymentResource.fromResource(p))
    }


}