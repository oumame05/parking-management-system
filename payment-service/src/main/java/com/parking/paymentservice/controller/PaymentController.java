package com.parking.paymentservice.controller;

import com.parking.paymentservice.model.Payment;
import com.parking.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    // GET - جلب كل المدفوعات
    @GetMapping
    public List<Payment> getAllPayments() {
        return service.getAllPayments();
    }

    // POST - إنشاء فاتورة جديدة
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return service.createPayment(payment);
    }

    // PUT - تأكيد الدفع
    @PutMapping("/{id}/confirm")
    public Payment confirmPayment(@PathVariable Long id) {
        return service.confirmPayment(id);
    }

    // PUT - إلغاء الدفع
    @PutMapping("/{id}/cancel")
    public Payment cancelPayment(@PathVariable Long id) {
        return service.cancelPayment(id);
    }

    // GET - جلب مدفوعات شخص معين
    @GetMapping("/owner/{ownerName}")
    public List<Payment> getByOwner(@PathVariable String ownerName) {
        return service.getByOwner(ownerName);
    }
}