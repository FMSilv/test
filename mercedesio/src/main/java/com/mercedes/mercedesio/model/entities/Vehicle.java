package com.mercedes.mercedesio.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "VEHICLES")
public class Vehicle implements Serializable {

    @Id
    @Column(name="ID", nullable = false)
    private String id;
    @Column(name="MODEL")
    private String model;
    @Column(name="FUEL")
    private String fuel;
    @Column(name="TRANSMISSION")
    private String transmission;

    //bi-directional many-to-one association to DEALER
    @ManyToOne
    @JoinColumn(name="DEALER_ID", referencedColumnName = "ID", nullable = false)
    @JsonBackReference
    private Dealer dealer;

    //bi-directional many-to-one association to VehicleAvailability
    @OneToMany(mappedBy="vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<VehicleAvailability> vehicleAvailabilityList;

    //bi-directional many-to-one association to Booking
    @OneToMany(mappedBy="vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
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

    public List<VehicleAvailability> getVehicleAvailabilityList() {
        return this.vehicleAvailabilityList;
    }

 /*   public void setVehicleAvailabilityList(List<VehicleAvailability> vehicleAvailabilityList) {
        this.vehicleAvailabilityList = vehicleAvailabilityList;
    }*/

    public VehicleAvailability addVehicleAvailability(VehicleAvailability vehicleAvailability) {
        getVehicleAvailabilityList().add(vehicleAvailability);
        vehicleAvailability.setVehicle(this);

        return vehicleAvailability;
    }

    public VehicleAvailability removeVehicleAvailability(VehicleAvailability vehicleAvailability) {
        getVehicleAvailabilityList().remove(vehicleAvailability);
        vehicleAvailability.setVehicle(null);

        return vehicleAvailability;
    }



    public List<Booking> getBookingList() {
        return this.bookingList;
    }

/*    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }*/

    public Booking addBooking(Booking booking) {
        getBookingList().add(booking);
        booking.setVehicle(this);
        return booking;
    }

    public Booking removeIvtDivergencia0(Booking booking) {
        getBookingList().remove(booking);
        booking.setVehicle(null);

        return booking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
