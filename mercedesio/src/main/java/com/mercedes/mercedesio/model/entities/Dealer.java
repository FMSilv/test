package com.mercedes.mercedesio.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DEALERS")
public class Dealer implements Serializable {

    @Id
    @Column(name="ID", nullable = false)
    private String id;
    @Column(name="NAME", nullable = false)
    private String name;
    @Column(name="LATITUDE")
    private double latitude;
    @Column(name="LONGITUDE")
    private double longitude;

    //bi-directional many-to-one association to DealerCloseTime
    @OneToMany(mappedBy="dealer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<DealerCloseTime> dealerCloseTimeList;

    //bi-directional many-to-one association to Vehicle
    @OneToMany(mappedBy="dealer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
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

    public List<Vehicle> getVehicleList() {
        return this.vehicleList;
    }

/*    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }*/

    public Vehicle addVehicle(Vehicle vehicle) {
        getVehicleList().add(vehicle);
        vehicle.setDealer(this);
        return vehicle;
    }

    public Vehicle removeVehicle(Vehicle vehicle) {
        getVehicleList().remove(vehicle);
        vehicle.setDealer(null);

        return vehicle;
    }


    public List<DealerCloseTime> getDealerCloseTimeList() {
        return this.dealerCloseTimeList;
    }

/*    public void setDealerCloseTimeList(List<DealerCloseTime> dealerCloseTimeList) {
        this.dealerCloseTimeList = dealerCloseTimeList;
    }*/

    public DealerCloseTime addDealerCloseTime(DealerCloseTime dealerCloseTime) {
        getDealerCloseTimeList().add(dealerCloseTime);
        dealerCloseTime.setDealer(this);
        return dealerCloseTime;
    }

    public DealerCloseTime removeDealerCloseTime(DealerCloseTime dealerCloseTime) {
        getDealerCloseTimeList().remove(dealerCloseTime);
        dealerCloseTime.setDealer(null);

        return dealerCloseTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
