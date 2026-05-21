package com.parking.paymentservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reservationId;      // رقم الحجز من parking-service

    private String ownerName;        // اسم صاحب السيارة

    private Double amount;           // المبلغ

    private String status;           // PENDING / PAID / CANCELLED

    private LocalDateTime paymentTime; // وقت الدفع
}