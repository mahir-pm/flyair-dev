package com.flyair.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PriceUpdateService {

    private Random random = new Random();

    // Simulate fetching updated flight price
    public double getUpdatedFlightPrice(double currentPrice) {
        double fluctuation = 0.9 + (1.2 - 0.9) * random.nextDouble(); // 90% to 120%
        return Math.round(currentPrice * fluctuation);
    }

    // Simulate fetching updated hotel price
    public double getUpdatedHotelPrice(double currentPrice) {
        double fluctuation = 0.95 + (1.1 - 0.95) * random.nextDouble(); // 95% to 110%
        return Math.round(currentPrice * fluctuation);
    }
}
