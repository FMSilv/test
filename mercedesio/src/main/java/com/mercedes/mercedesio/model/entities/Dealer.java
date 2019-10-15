package com.mercedes.mercedesio.model.entities;

import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Setter
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
    @OneToMany(mappedBy="dealer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    /*@Fetch(value = FetchMode.SUBSELECT)*/
    private List<DealerCloseTime> dealerCloseTimeList;

    //bi-directional many-to-one association to Vehicle
    @OneToMany(mappedBy="dealer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    /*@Fetch(value = FetchMode.SUBSELECT)*/
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

    public DealerCloseTime addDealerCloseTime(DealerCloseTime dealerCloseTime) {
        getDealerCloseTimeList().add(dealerCloseTime);
        dealerCloseTime.setDealer(this);

        return dealerCloseTime;
    }

    public Vehicle removeVehicle(Vehicle vehicle) {
        getVehicleList().remove(vehicle);
        vehicle.setDealer(null);

        return vehicle;
    }


/*    public List<Vehicle> getVehicleList() {
        return this.vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        getVehicleList().add(vehicle);
        vehicle.setDealer(this);

        return vehicle;
    }

    public Vehicle removeVehicle(Vehicle vehicle) {
        getVehicleList().remove(vehicle);
        vehicle.setDealer(null);

        return vehicle;
    }*/

}
