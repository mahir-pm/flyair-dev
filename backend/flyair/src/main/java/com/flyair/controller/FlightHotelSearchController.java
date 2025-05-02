package com.flyair.controller;

import com.flyair.model.Flight;
import com.flyair.model.Hotel;
import com.flyair.service.FlightHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class FlightHotelSearchController {

    @Autowired
    private FlightHotelService flightHotelService;

    // Existing endpoints
    @GetMapping("/flights")
    public List<Flight> searchFlights(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        return flightHotelService.searchFlights(source, destination, minPrice, maxPrice);
    }

    @GetMapping("/hotels")
    public List<Hotel> searchHotels(
            @RequestParam String location,
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        return flightHotelService.searchHotels(location, minPrice, maxPrice);
    }

    // Search and Compare Flights + Hotels
    @GetMapping("/comparison")
    public Map<String, Object> compareFlightsAndHotels(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam String location,
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        return flightHotelService.compareFlightsAndHotels(source, destination, location, minPrice, maxPrice);
    }
}
