package com.parking.parkingservice.repository;

import com.parking.parkingservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // جلب كل حجوزات مكان معين
    List<Reservation> findBySpotId(Long spotId);

    // جلب كل حجوزات شخص معين
    List<Reservation> findByOwnerName(String ownerName);
}