package com.booking.service;

import com.booking.Exception.BookingNotFoundException;
import com.booking.models.Booking;
import com.booking.models.HotelRooms;
import com.booking.repository.BookingRespository;
import com.booking.repository.HotelRoomsRepository;
import com.booking.util.BookingSequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRespository bookingRepository;

    @Autowired
    HotelRoomsRepository hotelRoomsRepository;

        public List<Booking> createBooking(Booking booking){
            List<HotelRooms> availableRooms = getAvailableRooms(booking.getCheckOutDate(), booking.getCheckInDate());
            List<Booking> savedBookings = new ArrayList<>();
            long sequenceNumber = BookingSequenceGenerator.generateSequenceNumber();
            Long sequenceNumberString = Long.parseLong(String.format("%010d", sequenceNumber));
            for(int i = 0; i < booking.getNumber_of_rooms(); i++){
                if(i >= availableRooms.size()) {
                    // there are not enough available rooms
                    throw new BookingNotFoundException("Not enough rooms available");
                }

                Booking bookingToSave = new Booking();
                bookingToSave.setCheckInDate(booking.getCheckInDate());
                bookingToSave.setCheckOutDate(booking.getCheckOutDate());
                bookingToSave.setBookingReference(sequenceNumberString);
                bookingToSave.setPersons(booking.getPersons());
                bookingToSave.setHotel_room_id(availableRooms.get(i).getId());
                Booking savedBooking = bookingRepository.save(bookingToSave);
                savedBookings.add(savedBooking);
            }

        return savedBookings;
    }

    public Page<Booking> getAllBookings(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return bookingRepository.findAll(pageable);
    }

    public List<Booking> getBookingList(int pageNumber, int pageSize, String sortBy) {
        Page<Booking> bookingPage = getAllBookings(pageNumber, pageSize, sortBy);
        return bookingPage.getContent();
    }

    public int getTotalPagesCount(int pageNumber, int pageSize, String sortBy) {
        Page<Booking> bookingPage = getAllBookings(pageNumber, pageSize, sortBy);
        return bookingPage.getTotalPages();
    }

    public long getTotalBookingsCount(int pageNumber, int pageSize, String sortBy) {
        Page<Booking> bookingPage = getAllBookings(pageNumber, pageSize, sortBy);
        return bookingPage.getTotalElements();
    }
    public Booking updateBooking(Booking newbooking, Long id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setBookingReference(newbooking.getBookingReference());
                    booking.setHotel_room_id(newbooking.getHotel_room_id());
                    booking.setPersons(newbooking.getPersons());
                    booking.setCheckInDate(newbooking.getCheckInDate());
                    booking.setCheckOutDate(newbooking.getCheckOutDate());
                    return bookingRepository.save(booking);
                })
                .orElseGet(() -> {
                    newbooking.setId(id);
                    return bookingRepository.save(newbooking);
                });
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }


    public List<HotelRooms> getAvailableRooms(Date checkInDate, Date checkOutDate) {
        List<HotelRooms> availableRooms = new ArrayList<>();

        // Get all bookings that overlap with the check-in and check-out dates
        List<Booking> overlappingBookings = bookingRepository.findByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(checkOutDate.toLocalDate(), checkInDate.toLocalDate());

        // Get all rooms in the hotel
        List<HotelRooms> allRooms = new ArrayList<>();
        for (HotelRooms room : hotelRoomsRepository.findAll()) {
            allRooms.add(room);
        }

        // Loop through all rooms and check if they are available during the given dates
        for (HotelRooms room : allRooms) {
            boolean isAvailable = true;
            for (Booking booking : overlappingBookings) {
                if (booking.getRooms().contains(room)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }
}
