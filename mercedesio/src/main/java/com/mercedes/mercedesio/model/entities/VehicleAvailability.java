package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "VEHICLE_AVAILABILIY")
public class VehicleAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    @Column(name="VEHICLE_ID")
    private String vehicleId;
    @Column(name="DAY")
    private Date day;
    @Column(name="HOUR")
    private Date hour;

}
