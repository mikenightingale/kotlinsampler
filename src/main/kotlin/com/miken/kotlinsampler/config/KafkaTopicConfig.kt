package com.miken.kotlinsampler.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka


@Configuration
@EnableKafka
class KafkaTopicConfig(

    @Value("\${topic.name}") var topicName: String
) {

    @Bean
    fun newTopic(): NewTopic? {
        return NewTopic(topicName, 2, 1.toShort())
    }

}