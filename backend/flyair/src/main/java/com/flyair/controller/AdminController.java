package com.flyair.controller;

import com.flyair.model.Flight;
import com.flyair.model.Hotel;
import com.flyair.service.AdminService;
import com.flyair.service.AdminService.AdminDashboardMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public AdminDashboardMetrics getDashboardMetrics(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String status
    ) {
        LocalDateTime start = startDate != null ? LocalDateTime.parse(startDate) : null;
        LocalDateTime end = endDate != null ? LocalDateTime.parse(endDate) : null;
        return adminService.getDashboardMetrics(start, end, location, status);
    }

    @PutMapping("/flight/update")
    public Flight updateFlight(@RequestParam Long flightId,
                                @RequestParam(required = false) Double price,
                                @RequestParam(required = false) Boolean available,
                                @RequestParam(required = false) String departureTime,
                                @RequestParam(required = false) String arrivalTime) {
        return adminService.updateFlight(flightId, price, available, departureTime, arrivalTime);
    }

    @PutMapping("/hotel/update")
    public Hotel updateHotel(@RequestParam Long hotelId,
                            @RequestParam(required = false) Double pricePerNight,
                            @RequestParam(required = false) Boolean available,
                            @RequestParam(required = false) Integer rating) {
        return adminService.updateHotel(hotelId, pricePerNight, available, rating);
    }
}
