package com.mercedes.mercedesio.repository;

import com.mercedes.mercedesio.model.entities.Dealer;
import com.mercedes.mercedesio.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, String> {

    public List<Vehicle> findByModel(String model);

    public List<Vehicle> findByFuel(String fuel);

    public List<Vehicle> findByTransmission(String transmission);

    public List<Vehicle> findByDealer(Dealer dealer);

    public List<Vehicle> findByModelAndFuelAndTransmission(String model, String fuel, String transmission);

}
