package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "VEHICLE")
public class Vehicle implements Serializable {

    @Id
    @Column(name="ID")
    private String id;
    @Column(name="MODEL")
    public String model;
    @Column(name="FUEL")
    private String fuel;
    @Column(name="TRANSMISSION")
    private String transmission;

    //bi-directional many-to-one association to DEALER
    @ManyToOne
    @JoinColumn(name="DEALER_ID")
    private Dealer dealer;

    //bi-directional many-to-one association to VehicleAvailability
    @OneToMany(mappedBy="vehicleId", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<VehicleAvailability> vehicleAvailabilityList;

    //bi-directional many-to-one association to Booking
    @OneToMany(mappedBy="vehicleId", fetch = FetchType.LAZY)
    private List<Booking> bookingList;

}
