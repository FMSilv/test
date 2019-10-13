package com.mercedes.mercedesio.model.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "DEALERS")
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
    @OneToMany(mappedBy="dealer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DealerCloseTime> dealerCloseTimeList;

    //bi-directional many-to-one association to Vehicle
    @OneToMany(mappedBy="dealer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Vehicle> vehicleList;

    public void setVehicleList(List<Vehicle> vehicleList) {
        for(Vehicle vehicle : vehicleList){
            vehicle.setDealer(this);
        }
        this.vehicleList = vehicleList;
    }

    public void setDealerCloseTimeList(List<DealerCloseTime> dealerCloseTimeList) {
        for(DealerCloseTime dealerCloseTime : dealerCloseTimeList){
            dealerCloseTime.setDealer(this);
        }
        this.dealerCloseTimeList = dealerCloseTimeList;
    }

}
