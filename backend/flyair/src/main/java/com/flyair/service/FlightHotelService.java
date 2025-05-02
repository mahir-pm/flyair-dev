package com.flyair.service;

import com.flyair.model.Flight;
import com.flyair.model.Hotel;
import com.flyair.repository.FlightRepository;
import com.flyair.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FlightHotelService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private HotelRepository hotelRepository;

    // Existing flight search with caching
    @Cacheable(value = "flights", key = "#source + '-' + #destination + '-' + #minPrice + '-' + #maxPrice")
    public List<Flight> searchFlights(String source, String destination, double minPrice, double maxPrice) {
        return flightRepository.findBySourceAndDestinationAndPriceBetweenAndAvailableTrue(source, destination, minPrice, maxPrice);
    }

    // Existing hotel search with caching
    @Cacheable(value = "hotels", key = "#location + '-' + #minPrice + '-' + #maxPrice")
    public List<Hotel> searchHotels(String location, double minPrice, double maxPrice) {
        return hotelRepository.findByLocationAndPricePerNightBetweenAndAvailableTrue(location, minPrice, maxPrice);
    }

    // NEW: Search and Compare Flights + Hotels
    public Map<String, Object> compareFlightsAndHotels(String source, String destination, String location, double minPrice, double maxPrice) {
        Map<String, Object> result = new HashMap<>();

        // Search for flights and hotels (with caching enabled)
        List<Flight> flights = searchFlights(source, destination, minPrice, maxPrice);
        List<Hotel> hotels = searchHotels(location, minPrice, maxPrice);

        // Find cheapest flight
        Flight bestFlight = flights.stream()
                .min(Comparator.comparingDouble(Flight::getPrice))
                .orElse(null);

        // Find cheapest hotel
        Hotel bestHotel = hotels.stream()
                .min(Comparator.comparingDouble(Hotel::getPricePerNight))
                .orElse(null);

        result.put("flights", flights);
        result.put("hotels", hotels);
        result.put("bestFlight", bestFlight);
        result.put("bestHotel", bestHotel);

        return result;
    }
}
