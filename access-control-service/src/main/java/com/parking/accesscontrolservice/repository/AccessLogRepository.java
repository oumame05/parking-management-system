package com.parking.accesscontrolservice.repository;

import com.parking.accesscontrolservice.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    // جلب سجلات سيارة معينة
    List<AccessLog> findByCarPlate(String carPlate);

    // جلب سجلات مكان معين
    List<AccessLog> findBySpotNumber(String spotNumber);

    // جلب الدخولات فقط أو الخروجات فقط
    List<AccessLog> findByAction(String action);
}