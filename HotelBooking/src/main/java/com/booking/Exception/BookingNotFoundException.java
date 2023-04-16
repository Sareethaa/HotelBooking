package com.booking.Exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String id) {
        super("Could not find Booking " + id);
    }

}
