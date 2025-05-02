package com.flyair.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long flightId;
    private Long hotelId;

    private boolean mealIncluded;
    private boolean extraLuggage;

    private double totalCost;

    private String bookingStatus; // Pending, Confirmed, Cancelled

    private LocalDateTime bookingDate;
    private LocalDateTime tripDate;

    private boolean cancelled; // true if booking is cancelled
}
