package com.mercedes.mercedesio.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "VEHICLES_AVAILABILIY")
public class VehicleAvailability implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable = false)
    private long id;
    @Column(name="DAY", nullable = false)
    private String day;
    @Column(name="HOUR", nullable = false)
    private String hour;

    //bi-directional many-to-one association to VEHICLE
    @ManyToOne
    @JoinColumn(name="VEHICLE_ID", referencedColumnName = "ID", nullable = false)
    @JsonBackReference
    private Vehicle vehicle;


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
