package com.mercedes.mercedesio.repository;

import com.mercedes.mercedesio.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, String> {



}
