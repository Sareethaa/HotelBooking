package com.booking.Exception;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(Long id) {
        super("Could not find Room " + id);

    }
}
