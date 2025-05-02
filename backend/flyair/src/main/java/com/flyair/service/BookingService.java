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

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private HotelRepository hotelRepository;

    private static final double MEAL_COST = 500.0;
    private static final double EXTRA_LUGGAGE_COST = 1000.0;

    public Booking createBooking(Long flightId, Long hotelId, boolean mealIncluded, boolean extraLuggage) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
    
        // Get real-time updated prices
        double updatedFlightPrice = priceUpdateService.getUpdatedFlightPrice(flight.getPrice());
        double updatedHotelPrice = priceUpdateService.getUpdatedHotelPrice(hotel.getPricePerNight());
    
        double totalCost = updatedFlightPrice + updatedHotelPrice;
        if (mealIncluded) totalCost += MEAL_COST;
        if (extraLuggage) totalCost += EXTRA_LUGGAGE_COST;
    
        Booking booking = new Booking();
        booking.setFlightId(flightId);
        booking.setHotelId(hotelId);
        booking.setMealIncluded(mealIncluded);
        booking.setExtraLuggage(extraLuggage);
        booking.setTotalCost(totalCost);
        booking.setBookingStatus("Pending");
        booking.setBookingDate(LocalDateTime.now());
        booking.setTripDate(LocalDateTime.now().plusDays(7)); // Assume trip after 7 days
        booking.setCancelled(false);
    
        return bookingRepository.save(booking);
    }
    

    public List<Booking> getBookingHistory(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null) {
            return bookingRepository.findByBookingDateBetween(startDate, endDate);
        } else {
            return bookingRepository.findAll();
        }
    }

    public Booking modifyBooking(Long bookingId, boolean mealIncluded, boolean extraLuggage) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.isCancelled()) {
            throw new RuntimeException("Cannot modify cancelled booking");
        }

        double totalCost = booking.getTotalCost();

        if (mealIncluded && !booking.isMealIncluded()) totalCost += MEAL_COST;
        if (extraLuggage && !booking.isExtraLuggage()) totalCost += EXTRA_LUGGAGE_COST;

        booking.setMealIncluded(mealIncluded);
        booking.setExtraLuggage(extraLuggage);
        booking.setTotalCost(totalCost);

        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setCancelled(true);
        booking.setBookingStatus("Cancelled");

        return bookingRepository.save(booking);
    }

    @Autowired
    private PriceUpdateService priceUpdateService;
    public double previewBookingCost(Long flightId, Long hotelId, boolean mealIncluded, boolean extraLuggage) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
    
        // Fetch real-time updated prices
        double updatedFlightPrice = priceUpdateService.getUpdatedFlightPrice(flight.getPrice());
        double updatedHotelPrice = priceUpdateService.getUpdatedHotelPrice(hotel.getPricePerNight());
    
        double totalCost = updatedFlightPrice + updatedHotelPrice;
    
        if (mealIncluded) {
            totalCost += 500; // example meal price
        }
        if (extraLuggage) {
            totalCost += 1000; // example luggage price
        }
    
        return totalCost;
    }
    
}
