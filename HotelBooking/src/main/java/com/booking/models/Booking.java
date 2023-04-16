package com.booking.models;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Booking")
@Entity
@Table
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long hotel_room_id;
    private long bookingReference;
    private int number_of_rooms;
    private int persons;
    private Date checkInDate;
    private Date checkOutDate;
    @Column
    @ElementCollection(targetClass=Integer.class)
    private List<HotelRooms> rooms;

    public Booking() {

    }

    public Booking(long hotel_room_id, long bookingReference, int persons, Date checkInDate, Date checkOutDate, int number_of_rooms, HotelRooms rooms) {
        this.hotel_room_id = hotel_room_id;
        this.bookingReference = bookingReference;
        this.persons = persons;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.number_of_rooms = number_of_rooms;
        this.rooms = (List<HotelRooms>) rooms;
    }
}
