package com.parking.parkingservice.service;

import com.parking.parkingservice.model.ParkingSpot;
import com.parking.parkingservice.model.Reservation;
import com.parking.parkingservice.repository.ParkingSpotRepository;
import com.parking.parkingservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ParkingSpotRepository spotRepository;
    private final RestTemplate restTemplate;

    // جلب كل الحجوزات
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // إنشاء حجز جديد
    public Reservation createReservation(Reservation reservation) {

        // 1 - تحقق أن المكان موجود
        ParkingSpot spot = spotRepository.findById(reservation.getSpot().getId())
                .orElseThrow(() -> new RuntimeException("المكان غير موجود!"));

        // 2 - تحقق أن المكان متاح
        if (!spot.isAvailable()) {
            throw new RuntimeException("المكان محجوز مسبقاً!");
        }

        // 3 - اجعل المكان غير متاح
        spot.setAvailable(false);
        spotRepository.save(spot);

        // 4 - احفظ الحجز
        Reservation saved = reservationRepository.save(reservation);

        // 5 - استدعاء payment-service
        Map<String, Object> payment = new HashMap<>();
        payment.put("reservationId", saved.getId());
        payment.put("ownerName", saved.getOwnerName());
        payment.put("amount", 50.0);
        restTemplate.postForObject(
                "http://localhost:8082/api/payments",
                payment,
                Map.class
        );

        // 6 - استدعاء access-control-service
        Map<String, Object> accessLog = new HashMap<>();
        accessLog.put("carPlate", saved.getCarPlate());
        accessLog.put("spotNumber", spot.getSpotNumber());
        restTemplate.postForObject(
                "http://localhost:8083/api/access/entry",
                accessLog,
                Map.class
        );

        return saved;
    }

    // إلغاء حجز
    public void cancelReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("الحجز غير موجود!"));

        // أعد المكان متاحاً
        ParkingSpot spot = reservation.getSpot();
        spot.setAvailable(true);
        spotRepository.save(spot);

        // تسجيل الخروج في access-control-service
        Map<String, Object> accessLog = new HashMap<>();
        accessLog.put("carPlate", reservation.getCarPlate());
        accessLog.put("spotNumber", spot.getSpotNumber());
        restTemplate.postForObject(
                "http://localhost:8083/api/access/exit",
                accessLog,
                Map.class
        );

        reservationRepository.deleteById(id);
    }
}