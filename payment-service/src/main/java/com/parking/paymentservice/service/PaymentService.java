package com.parking.paymentservice.service;

import com.parking.paymentservice.model.Payment;
import com.parking.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // جلب كل المدفوعات
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // إنشاء فاتورة جديدة
    public Payment createPayment(Payment payment) {
        payment.setStatus("PENDING");
        payment.setPaymentTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    // تأكيد الدفع
    public Payment confirmPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("الفاتورة غير موجودة!"));
        payment.setStatus("PAID");
        payment.setPaymentTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    // إلغاء الدفع
    public Payment cancelPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("الفاتورة غير موجودة!"));
        payment.setStatus("CANCELLED");
        return paymentRepository.save(payment);
    }

    // جلب مدفوعات شخص معين
    public List<Payment> getByOwner(String ownerName) {
        return paymentRepository.findByOwnerName(ownerName);
    }
}