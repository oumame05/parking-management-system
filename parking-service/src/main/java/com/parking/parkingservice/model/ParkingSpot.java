package com.parking.parkingservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "parking_spots")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotNumber;   // رقم المكان مثل "A1", "B2"

    private boolean available;   // هل المكان متاح؟

    private String type;         // نوع المكان: "VIP", "NORMAL"
}