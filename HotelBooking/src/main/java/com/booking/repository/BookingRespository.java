package com.booking.repository;

import com.booking.models.Booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRespository extends JpaRepository<Booking,Long> {
    Page<Booking> findAll(Pageable pageable);
    List<Booking> findByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(LocalDate checkOutDate, LocalDate checkInDate);

}
