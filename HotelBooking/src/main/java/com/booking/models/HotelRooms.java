package com.booking.models;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "HotelRooms")

@Entity
@Table
@Data
public class HotelRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private int roomNumber;
    private int floorNumber;
    private int capacity;
    private double pricePerNight;
    public HotelRooms() {
    }
    public HotelRooms(long id, int roomNumber, int floorNumber, int capacity, double pricePerNight) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
    }


}
