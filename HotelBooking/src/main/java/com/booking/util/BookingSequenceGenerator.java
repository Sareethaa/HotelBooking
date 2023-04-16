package com.booking.util;

public class BookingSequenceGenerator {
    private static long lastSequenceNumber = 0;

    public static synchronized long generateSequenceNumber() {
        lastSequenceNumber++;
        return lastSequenceNumber;
    }

    public static synchronized void resetSequenceNumber() {
        lastSequenceNumber = 0;
    }
}

