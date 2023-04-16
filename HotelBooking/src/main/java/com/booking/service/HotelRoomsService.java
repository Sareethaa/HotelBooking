package com.booking.service;

import com.booking.models.HotelRooms;
import com.booking.repository.HotelRoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HotelRoomsService {

    @Autowired
    HotelRoomsRepository hotelRoomsRepository;

    public List<HotelRooms> getRooms(){
        List<HotelRooms> allRooms = hotelRoomsRepository.findAll();
        return allRooms;
    }


}
