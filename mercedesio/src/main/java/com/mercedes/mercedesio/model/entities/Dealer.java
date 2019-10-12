package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "DEALER")
public class Dealer {

    @Id
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private long NumberOfCars;

}
