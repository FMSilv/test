package com.mercedes.mercedesio.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "DEALER")
public class Dealer implements Serializable {

    @Id
    @Column(name="ID")
    private String id;
    @Column(name="NAME")
    private String name;
    @Column(name="LATITUDE")
    private double latitude;
    @Column(name="LONGITUDE")
    private double longitude;

    //bi-directional many-to-one association to DealerCloseTime
    @OneToMany(mappedBy="dealer", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<DealerCloseTime> dealerCloseTimeList;

    //bi-directional many-to-one association to Vehicle
    @OneToMany(mappedBy="dealer", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Vehicle> vehicleList;

}
