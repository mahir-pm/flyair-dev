package com.flyair.service;

import com.flyair.model.Booking;
import com.flyair.model.Flight;
import com.flyair.model.Hotel;
import com.flyair.repository.BookingRepository;
import com.flyair.repository.FlightRepository;
import com.flyair.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public AdminDashboardMetrics getDashboardMetrics(LocalDateTime startDate, LocalDateTime endDate, String location, String status) {
        List<Booking> bookings = bookingRepository.findAll();

        if (startDate != null && endDate != null) {
            bookings = bookings.stream()
                    .filter(b -> !b.getBookingDate().isBefore(startDate) && !b.getBookingDate().isAfter(endDate))
                    .collect(Collectors.toList());
        }

        if (location != null && !location.isEmpty()) {
            bookings = bookings.stream()
                    .filter(b -> {
                        Flight flight = flightRepository.findById(b.getFlightId()).orElse(null);
                        Hotel hotel = hotelRepository.findById(b.getHotelId()).orElse(null);
                        return (flight != null && (flight.getSource().equalsIgnoreCase(location) || flight.getDestination().equalsIgnoreCase(location)))
                                || (hotel != null && hotel.getLocation().equalsIgnoreCase(location));
                    })
                    .collect(Collectors.toList());
        }

        if (status != null && !status.isEmpty()) {
            bookings = bookings.stream()
                    .filter(b -> b.getBookingStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }

        long totalBookings = bookings.size();
        double totalRevenue = bookings.stream()
                .filter(b -> !b.isCancelled() && "Paid".equalsIgnoreCase(b.getBookingStatus()))
                .mapToDouble(Booking::getTotalCost)
                .sum();
        long totalCancellations = bookings.stream()
                .filter(Booking::isCancelled)
                .count();

        return new AdminDashboardMetrics(totalBookings, totalRevenue, totalCancellations);
    }

    // Inner class to hold metrics response
    public static class AdminDashboardMetrics {
        private long totalBookings;
        private double totalRevenue;
        private long totalCancellations;

        public AdminDashboardMetrics(long totalBookings, double totalRevenue, long totalCancellations) {
            this.totalBookings = totalBookings;
            this.totalRevenue = totalRevenue;
            this.totalCancellations = totalCancellations;
        }

        public long getTotalBookings() {
            return totalBookings;
        }

        public double getTotalRevenue() {
            return totalRevenue;
        }

        public long getTotalCancellations() {
            return totalCancellations;
        }
    }

    public Flight updateFlight(Long flightId, Double price, Boolean available, String departureTime, String arrivalTime) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    
        if (price != null) flight.setPrice(price);
        if (available != null) flight.setAvailable(available);
        if (departureTime != null) flight.setDepartureTime(departureTime);
        if (arrivalTime != null) flight.setArrivalTime(arrivalTime);
    
        return flightRepository.save(flight);
    }
    
    public Hotel updateHotel(Long hotelId, Double pricePerNight, Boolean available, Integer rating) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
    
        if (pricePerNight != null) hotel.setPricePerNight(pricePerNight);
        if (available != null) hotel.setAvailable(available);
        if (rating != null) hotel.setRating(rating);
    
        return hotelRepository.save(hotel);
    }
}
