package com.mercedes.mercedesio.service;

import com.mercedes.mercedesio.model.entities.Booking;
import com.mercedes.mercedesio.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private IBookingRepository bookingRepository;

    @Autowired
    public BookingService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void saveEntity(Booking booking){
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



}
