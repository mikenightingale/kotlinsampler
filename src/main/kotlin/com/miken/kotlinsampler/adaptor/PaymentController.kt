package com.miken.kotlinsampler.adaptor

import com.miken.kotlinsampler.adaptor.resource.PaymentResource
import com.miken.kotlinsampler.implementation.action.PaymentActions
import com.miken.kotlinsampler.implementation.kafka.PaymentProducer
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/payment"], consumes = ["application/json"])
@Slf4j
class PaymentController(
    private val paymentActions: PaymentActions,
    private val paymentProducer: PaymentProducer,
) {

    @PostMapping
    fun createPayment(@RequestBody p: PaymentResource): ResponseEntity<PaymentResource> {
        paymentProducer.send(p.bic, p);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    fun getPayments(): ResponseEntity<List<PaymentResource>> {
        val all = paymentActions.listAll().stream().map(PaymentResource.Companion::fromEntity).toList();
        return ResponseEntity.ok(all);
    }


}