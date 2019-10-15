package com.mercedes.mercedesio.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn(name="VEHICLE_ID", referencedColumnName = "ID", nullable = false)
    @JsonBackReference
    private Vehicle vehicle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public String getCancelledReason() {
        return cancelledReason;
    }

    public void setCancelledReason(String cancelledReason) {
        this.cancelledReason = cancelledReason;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
