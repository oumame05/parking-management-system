package com.parking.accesscontrolservice.controller;

import com.parking.accesscontrolservice.model.AccessLog;
import com.parking.accesscontrolservice.service.AccessControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/access")
@RequiredArgsConstructor
public class AccessControlController {

    private final AccessControlService service;

    // GET - جلب كل السجلات
    @GetMapping
    public List<AccessLog> getAllLogs() {
        return service.getAllLogs();
    }

    // POST - تسجيل دخول سيارة
    @PostMapping("/entry")
    public AccessLog registerEntry(@RequestBody AccessLog log) {
        return service.registerEntry(log);
    }

    // POST - تسجيل خروج سيارة
    @PostMapping("/exit")
    public AccessLog registerExit(@RequestBody AccessLog log) {
        return service.registerExit(log);
    }

    // GET - جلب سجلات سيارة معينة
    @GetMapping("/car/{carPlate}")
    public List<AccessLog> getByCarPlate(@PathVariable String carPlate) {
        return service.getByCarPlate(carPlate);
    }
}