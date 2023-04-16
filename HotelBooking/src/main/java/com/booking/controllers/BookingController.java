package com.booking.controllers;

import com.booking.models.Booking;
import com.booking.models.HotelRooms;
import com.booking.repository.HotelRoomsRepository;
import com.booking.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
public class BookingController {
    @Autowired

    HotelRoomsRepository  hotelRoomsRepository;

    @Autowired
    BookingService bookingService;

    @Operation(summary = "Get List of all rooms available in the Hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the bookings", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))
            }),
            @ApiResponse(responseCode = "400", description = "Can not find the bookings", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Exception to find the bookings", content = @Content),

    })
    @GetMapping("/getAllrooms")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    List<HotelRooms> all() {
        return hotelRoomsRepository.findAll();
    }

    @Operation(summary = "Create booking for the selected dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created the  the booking", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))
            }),
            @ApiResponse(responseCode = "400", description = "Can not find the rooms", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Exception to create the bookings", content = @Content),

    })
    @PostMapping("/createBookings")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Booking> createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @Operation(summary = "Update Booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the bookings", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))
            }),
            @ApiResponse(responseCode = "400", description = "Can not find the Id of the bookings", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Exception to find the bookings", content = @Content),

    })
    @PutMapping("/updateBooking/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Booking updateBooking(@RequestBody Booking updateBooking, @PathVariable Long id) {
        return bookingService.updateBooking(updateBooking, id);
    }

    @Operation(summary = "Delete booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the booking", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))
            }),
            @ApiResponse(responseCode = "400", description = "Can not find the booking id to delete", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Exception to find the bookings", content = @Content),

    })
    @DeleteMapping("/deleteBooking/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

    @Operation(summary = "Get all bookings in a number pages by giving size and sort by field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the bookings", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))
            }),
            @ApiResponse(responseCode = "400", description = "Can not find the bookings", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Exception to find the bookings", content = @Content),

    })
    @GetMapping("/getAllBookings/{pageNumber}/{pageSize}/{sortBy}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Page<Booking> getAllBookings(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String sortBy) {
        return bookingService.getAllBookings(pageNumber, pageSize, sortBy);
    }

    @Operation(summary = "Get List of all bookings in a number pages by giving size and sort by field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the bookings", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))
            }),
            @ApiResponse(responseCode = "400", description = "Can not find the bookings", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Exception to find the bookings", content = @Content),

    })
    @GetMapping("/getBookingList/{pageNumber}/{pageSize}/{sortBy}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Booking> getBookingList(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String sortBy) {
        return bookingService.getBookingList(pageNumber, pageSize, sortBy);
    }

    @Operation(summary = "Get total pages count for all bookings in a number pages by giving size and sort by field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the bookings", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))
            }),
            @ApiResponse(responseCode = "400", description = "Can not find the bookings", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Exception to find the bookings", content = @Content),

    })
    @GetMapping("/getTotalPagesCount/{pageNumber}/{pageSize}/{sortBy}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public int getTotalPagesCount(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String sortBy) {
        return bookingService.getTotalPagesCount(pageNumber, pageSize, sortBy);
    }

    @Operation(summary = "Get total bookings count in a number pages by giving size and sort by field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the bookings", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))
            }),
            @ApiResponse(responseCode = "400", description = "Can not find the bookings", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Exception to find the bookings", content = @Content),

    })
    @GetMapping("/getTotalBookingsCount/{pageNumber}/{pageSize}/{sortBy}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public long getTotalBookingsCount(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String sortBy) {
        return bookingService.getTotalBookingsCount(pageNumber, pageSize, sortBy);
    }


}
