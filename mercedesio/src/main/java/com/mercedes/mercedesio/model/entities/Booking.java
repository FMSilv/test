package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "BOOKING")
public class Booking {

    @Id
    private String id;
    private String vehicleId;
    private String firstName;
    private String lastName;
    private Date pickupDate;
    private Date createdAt;

}
