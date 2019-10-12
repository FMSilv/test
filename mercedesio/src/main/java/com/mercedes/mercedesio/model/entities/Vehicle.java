package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "VEHICLE")
public class Vehicle {

    @Id
    private String id;
    private String dealerId;
    public String model;
    private String fuel;
    private String transmission;

}
