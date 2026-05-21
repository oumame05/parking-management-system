package com.parking.paymentservice.repository;

import com.parking.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // جلب كل مدفوعات شخص معين
    List<Payment> findByOwnerName(String ownerName);

    // جلب كل مدفوعات حجز معين
    List<Payment> findByReservationId(Long reservationId);

    // جلب المدفوعات حسب الحالة
    List<Payment> findByStatus(String status);
}