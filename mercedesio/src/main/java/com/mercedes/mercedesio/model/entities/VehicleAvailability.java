package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
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
    @JoinColumn(name="VEHICLE_ID", nullable = false)
    private Vehicle vehicle;

}
