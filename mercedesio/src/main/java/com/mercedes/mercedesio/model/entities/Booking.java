package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BOOKINGS")
public class Booking {

    @Id
    @Column(name="ID")
    private String id;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="PICKUP_DATE")
    private String pickupDate;
    @Column(name="CREATED_AT")
    private String createdAt;

    //bi-directional many-to-one association to VEHICLE
    @ManyToOne
    @JoinColumn(name="VEHICLE_ID", nullable = false)
    private Vehicle vehicle;

}
