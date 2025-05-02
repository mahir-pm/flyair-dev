package com.flyair.service;

import com.flyair.model.Booking;
import com.flyair.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private BookingRepository bookingRepository;

    public String processPayment(Long bookingId, String paymentMethod) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.isCancelled()) {
            throw new RuntimeException("Cannot pay for a cancelled booking.");
        }

        if ("Paid".equalsIgnoreCase(booking.getBookingStatus())) {
            return "Booking is already paid.";
        }

        // Simulate Encryption Process
        System.out.println("Encrypting payment details securely...");
        System.out.println("Payment method selected: " + paymentMethod);
        System.out.println("Encryption successful ✅");

        // Simulate payment processing
        switch (paymentMethod.toLowerCase()) {
            case "creditcard":
            case "debitcard":
            case "paypal":
            case "upi":
                booking.setBookingStatus("Paid");
                bookingRepository.save(booking);
                return "Payment successful using " + paymentMethod + ". Booking marked as Paid.";
            default:
                throw new RuntimeException("Invalid payment method. Please select CreditCard, DebitCard, PayPal, or UPI.");
        }
    }
}
