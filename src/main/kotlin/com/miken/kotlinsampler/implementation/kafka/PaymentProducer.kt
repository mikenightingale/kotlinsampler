package com.miken.kotlinsampler.implementation.kafka

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
private const val TOPIC = "payment";

@Component
class PaymentProducer(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    fun send(key: String, msg: Any?) {
        kafkaTemplate.send(TOPIC, key, msg)
            .whenComplete { data, ex ->
                if (ex == null) {
                    logger.info { "message sent" }
                } else {
                    logger.error { "error sending kafka message" }
                    throw ex
                }
            }
    }
}