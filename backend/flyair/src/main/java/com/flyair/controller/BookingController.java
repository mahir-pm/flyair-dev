package com.flyair.controller;

import com.flyair.model.Booking;
import com.flyair.service.BookingService;
import com.flyair.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public Booking createBooking(@RequestParam Long flightId,
                                  @RequestParam Long hotelId,
                                  @RequestParam(defaultValue = "false") boolean mealIncluded,
                                  @RequestParam(defaultValue = "false") boolean extraLuggage) {
        return bookingService.createBooking(flightId, hotelId, mealIncluded, extraLuggage);
    }

    @GetMapping("/history")
    public List<Booking> getBookingHistory(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return bookingService.getBookingHistory(startDate, endDate);
    }

    @PutMapping("/modify")
    public Booking modifyBooking(@RequestParam Long bookingId,
                                  @RequestParam(defaultValue = "false") boolean mealIncluded,
                                  @RequestParam(defaultValue = "false") boolean extraLuggage) {
        return bookingService.modifyBooking(bookingId, mealIncluded, extraLuggage);
    }

    @PutMapping("/cancel")
    public Booking cancelBooking(@RequestParam Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    @GetMapping("/preview")
    public double previewBooking(@RequestParam Long flightId,
                                @RequestParam Long hotelId,
                                @RequestParam(defaultValue = "false") boolean mealIncluded,
                                @RequestParam(defaultValue = "false") boolean extraLuggage) {
        return bookingService.previewBookingCost(flightId, hotelId, mealIncluded, extraLuggage);
    }

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public String makePayment(@RequestParam Long bookingId,
                            @RequestParam String paymentMethod) {
        return paymentService.processPayment(bookingId, paymentMethod);
    }

}
