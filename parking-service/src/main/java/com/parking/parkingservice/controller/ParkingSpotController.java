package com.parking.parkingservice.controller;

import com.parking.parkingservice.model.ParkingSpot;
import com.parking.parkingservice.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService service;

    // GET - جلب كل الأماكن
    @GetMapping
    public List<ParkingSpot> getAllSpots() {
        return service.getAllSpots();
    }

    // POST - إضافة مكان جديد
    @PostMapping
    public ParkingSpot addSpot(@RequestBody ParkingSpot spot) {
        return service.addSpot(spot);
    }

    // DELETE - حذف مكان
    @DeleteMapping("/{id}")
    public void deleteSpot(@PathVariable Long id) {
        service.deleteSpot(id);
    }
}