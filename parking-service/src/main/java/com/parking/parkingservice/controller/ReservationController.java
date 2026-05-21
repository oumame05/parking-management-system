package com.parking.parkingservice.controller;

import com.parking.parkingservice.model.Reservation;
import com.parking.parkingservice.repository.ReservationRepository;
import com.parking.parkingservice.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;
    private final ReservationRepository reservationRepository;

    // GET - جلب كل الحجوزات
    @GetMapping
    public List<Reservation> getAllReservations() {
        return service.getAllReservations();
    }

    // POST - إنشاء حجز جديد
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return service.createReservation(reservation);
    }

    // DELETE - إلغاء حجز
    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long id) {
        service.cancelReservation(id);
    }

    // GET - جلب حجوزات مكان معين
    @GetMapping("/spot/{spotId}")
    public List<Reservation> getBySpot(@PathVariable Long spotId) {
        return reservationRepository.findBySpotId(spotId);
    }

    // GET - جلب حجوزات شخص معين
    @GetMapping("/owner/{ownerName}")
    public List<Reservation> getByOwner(@PathVariable String ownerName) {
        return reservationRepository.findByOwnerName(ownerName);
    }
}