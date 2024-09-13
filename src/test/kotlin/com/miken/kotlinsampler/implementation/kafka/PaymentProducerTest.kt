package com.miken.kotlinsampler.implementation.kafka

import com.miken.kotlinsampler.adaptor.resource.PaymentResource
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.KafkaMessageListenerContainer
import org.springframework.kafka.listener.MessageListener
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.ContainerTestUtils
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit


private const val MSG_KEY = "bic"
private const val TOPIC = "payment"

@SpringBootTest
@EmbeddedKafka(topics = [TOPIC], bootstrapServersProperty = "spring.kafka.bootstrap-servers")
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentProducerTest{

    @Autowired
    lateinit var producer: PaymentProducer

    val records: BlockingQueue<ConsumerRecord<String, Any>> = LinkedBlockingQueue()
    var container: KafkaMessageListenerContainer<String, Any>? = null

    @Autowired
    lateinit var embeddedKafkaBroker: EmbeddedKafkaBroker


    @BeforeAll
    fun setUp() {
        val configs = HashMap(KafkaTestUtils.consumerProps("consumer", "false", embeddedKafkaBroker))
        val valueDeserializer = JsonDeserializer<Any>()
        valueDeserializer.addTrustedPackages("com.miken.kotlinsampler.adaptor.resource")

        val consumerFactory = DefaultKafkaConsumerFactory(configs, StringDeserializer(), valueDeserializer)
        val containerProperties = ContainerProperties(TOPIC)

        container = KafkaMessageListenerContainer(consumerFactory, containerProperties)
        container!!.setupMessageListener(MessageListener(records::add))
        container!!.start()

        ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic())
    }

    @AfterAll
    fun tearDown() {
        container!!.stop()
    }


    @Test
    fun shouldListenForMessage() {

        val msg = PaymentResource(null, "bic", "iban", "EUR", 100)
        givenPaymentMsgIsSend(msg)

        val poll = records.poll(1000, TimeUnit.MILLISECONDS)
        assertThat(poll).isNotNull
        assertThat(poll!!.value()).isEqualTo(msg)
    }

    private fun givenPaymentMsgIsSend(paymentResource: PaymentResource) {
        producer.send(MSG_KEY, paymentResource)
    }


}