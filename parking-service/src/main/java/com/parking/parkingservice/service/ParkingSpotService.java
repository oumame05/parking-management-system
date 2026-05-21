package com.parking.parkingservice.service;

import com.parking.parkingservice.model.ParkingSpot;
import com.parking.parkingservice.repository.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpotService {

    private final ParkingSpotRepository repository;

    // جلب كل الأماكن
    public List<ParkingSpot> getAllSpots() {
        return repository.findAll();
    }

    // إضافة مكان جديد
    public ParkingSpot addSpot(ParkingSpot spot) {
        return repository.save(spot);
    }

    // حذف مكان
    public void deleteSpot(Long id) {
        repository.deleteById(id);
    }
}