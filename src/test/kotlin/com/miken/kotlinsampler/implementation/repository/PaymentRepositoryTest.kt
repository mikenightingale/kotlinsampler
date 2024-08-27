package com.miken.kotlinsampler.implementation.repository

import com.miken.kotlinsampler.model.Payment
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
@AutoConfigureEmbeddedDatabase(refresh = AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_EACH_TEST_METHOD)
class PaymentRepositoryTest {

    @Autowired
    lateinit var genericRepository: PaymentRepository

    @Test
    fun shouldSavePayment() {
        val p = providePayment()
        val result = genericRepository.save(p)
        assertThat(result.id).isGreaterThanOrEqualTo(1)
        assertThat(result.toString()).endsWith("bic=DE1234567, iban=DE123456789123, currency=EUR, amount=99)")
    }

    @Test
    fun shouldReadBackPaymentById() {
        val save = genericRepository.save(providePayment())
        val byId = genericRepository.findById(save.id!!)
        assertThat(byId).isPresent()
        assertThat(byId).hasValueSatisfying { s -> assertThat(s.toString()).isEqualTo("Payment(id=1, bic=DE1234567, iban=DE123456789123, currency=EUR, amount=99)") }
    }

    @Test
    fun shouldReadBackAllPayment() {
        genericRepository.save(providePayment())
        genericRepository.save(providePayment())
        val result = genericRepository.findAll()
        assertThat(result).hasSize(2)
    }

    fun providePayment(): Payment {
        return Payment(
            iban = "DE123456789123",
            bic = "DE1234567",
            currency = "EUR",
            amount = 99
        )

    }
}
