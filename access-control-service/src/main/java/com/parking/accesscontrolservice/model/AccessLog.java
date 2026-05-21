package com.parking.accesscontrolservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "access_logs")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carPlate;         // رقم اللوحة

    private String spotNumber;       // رقم المكان

    private String action;           // ENTRY أو EXIT

    private LocalDateTime accessTime; // وقت الدخول أو الخروج

    private boolean authorized;      // هل مسموح له؟
}