package com.parking.accesscontrolservice.service;

import com.parking.accesscontrolservice.model.AccessLog;
import com.parking.accesscontrolservice.repository.AccessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessControlService {

    private final AccessLogRepository accessLogRepository;

    // جلب كل السجلات
    public List<AccessLog> getAllLogs() {
        return accessLogRepository.findAll();
    }

    // تسجيل دخول سيارة
    public AccessLog registerEntry(AccessLog log) {
        log.setAction("ENTRY");
        log.setAccessTime(LocalDateTime.now());
        log.setAuthorized(true);
        return accessLogRepository.save(log);
    }

    // تسجيل خروج سيارة
    public AccessLog registerExit(AccessLog log) {
        log.setAction("EXIT");
        log.setAccessTime(LocalDateTime.now());
        log.setAuthorized(true);
        return accessLogRepository.save(log);
    }

    // جلب سجلات سيارة معينة
    public List<AccessLog> getByCarPlate(String carPlate) {
        return accessLogRepository.findByCarPlate(carPlate);
    }
}