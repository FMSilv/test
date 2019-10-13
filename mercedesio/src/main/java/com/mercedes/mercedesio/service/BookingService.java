package com.mercedes.mercedesio.service;

import com.mercedes.mercedesio.model.entities.Booking;
import com.mercedes.mercedesio.model.entities.Vehicle;
import com.mercedes.mercedesio.repository.IBookingRepository;
import com.mercedes.mercedesio.repository.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class BookingService {

    private IBookingRepository bookingRepository;
    private IVehicleRepository vehicleRepository;

    @Autowired
    public BookingService(IBookingRepository bookingRepository, IVehicleRepository vehicleRepository) {
        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public void checkAndSaveEntity(Booking booking) throws Exception {
        boolean flagAlreadyBooked = false;
        Vehicle vehicle = vehicleRepository.findById(booking.getVehicle().getId()).get();
        for(Booking vehicleBookings : vehicle.getBookingList()){
            if(vehicleBookings.getPickupDate().equals(booking.getPickupDate()))
            {
                /*flagAlreadyBooked = true;*/
                throw new Exception("Vehicle: "+vehicle.getId()+" has a booking for: "+vehicleBookings.getPickupDate()
                        +"\nYou are trying to book the vehicle: "+booking.getVehicle().getId()+" to: "+booking.getPickupDate());
            }
        }
        if(!flagAlreadyBooked)
        {
            bookingRepository.save(booking);
        }
    }

    public void saveEntity(Booking booking) throws Exception {
        if(null == booking)
        {
            throw new Exception("Failed to book the test drive.");
        }
        bookingRepository.save(booking);
    }

    public Booking getBookingById(String bookingId) throws Exception {
        Booking booking = bookingRepository.findById(bookingId).get();
        if(null == booking)
        {
            throw new Exception("Vehicle not found!");
        }
        return booking;
    }

    public Booking getBookByFirstNameAndLastNameAndPickupDate(String firstName, String lastName, String pickupDate) throws Exception {
        Booking booking = bookingRepository.findByFistnameAndLastNameAndPickupdate(firstName, lastName, pickupDate);
        if(null == booking)
        {
            throw new Exception("Vehicle not found!");
        }
        return booking;
    }

    public void cancelBooking(String firstName, String lastName, String pickupDate, String cancelledReason) throws Exception {
        Booking booking = getBookByFirstNameAndLastNameAndPickupDate(firstName, lastName, pickupDate);
        booking.setCancelledReason(cancelledReason);
        booking.setCancelledAt(LocalDateTime.now());
        bookingRepository.delete(booking);
    }


}
