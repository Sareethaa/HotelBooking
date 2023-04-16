package com.booking.repository;

import com.booking.models.HotelRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomsRepository extends JpaRepository<HotelRooms, Long> {
}
