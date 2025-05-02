package com.flyair.repository;

import com.flyair.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
