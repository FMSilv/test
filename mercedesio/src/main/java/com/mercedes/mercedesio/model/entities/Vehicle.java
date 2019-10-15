package com.mercedes.mercedesio.model.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "VEHICLES")
public class Vehicle implements Serializable {

    @Id
    @Column(name="ID", nullable = false)
    private String id;
    @Column(name="MODEL")
    public String model;
    @Column(name="FUEL")
    private String fuel;
    @Column(name="TRANSMISSION")
    private String transmission;

    //bi-directional many-to-one association to DEALER
    @ManyToOne
    @JoinColumn(name="DEALER_ID", nullable = false)
    private Dealer dealer;

    //bi-directional many-to-one association to VehicleAvailability
    @OneToMany(mappedBy="vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    /*@Fetch(value = FetchMode.SUBSELECT)*/
    private List<VehicleAvailability> vehicleAvailabilityList;

    //bi-directional many-to-one association to Booking
    @OneToMany(mappedBy="vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    /*@Fetch(value = FetchMode.SUBSELECT)*/
    private List<Booking> bookingList;

    public void setVehicleAvailabilityList(List<VehicleAvailability> vehicleAvailabilityList) {
        for(VehicleAvailability vehicleAvailability : vehicleAvailabilityList){
            vehicleAvailability.setVehicle(this);
        }
        this.vehicleAvailabilityList = vehicleAvailabilityList;
    }

    public void setBookingList(List<Booking> bookingList) {
        for(Booking booking : bookingList){
            booking.setVehicle(this);
        }
        this.bookingList = bookingList;
    }

}
