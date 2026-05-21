package com.parking.parkingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;        // اسم صاحب السيارة

    private String carPlate;         // رقم اللوحة

    private LocalDateTime startTime; // وقت بداية الحجز

    private LocalDateTime endTime;   // وقت نهاية الحجز

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private ParkingSpot spot;        // المكان المحجوز
}