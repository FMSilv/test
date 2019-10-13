package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "BOOKINGS")
public class Booking {

    @Id
    @Column(name="ID", nullable = false)
    private String id;
    @Column(name="FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="PICKUP_DATE", nullable = false)
    private String pickupDate;
    @Column(name="CREATED_AT", nullable = false)
    private String createdAt;
    @Column(name="CANCELLED_AT")
    private LocalDateTime cancelledAt;
    @Column(name="CANCELLED_REASON")
    private String cancelledReason;

    //bi-directional many-to-one association to VEHICLE
    @ManyToOne
    @JoinColumn(name="VEHICLE_ID", nullable = false)
    private Vehicle vehicle;

}
