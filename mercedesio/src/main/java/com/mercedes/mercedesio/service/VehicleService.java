package com.mercedes.mercedesio.service;

import com.mercedes.mercedesio.model.entities.Dealer;
import com.mercedes.mercedesio.model.entities.Vehicle;
import com.mercedes.mercedesio.repository.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private IVehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(IVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    public Vehicle getVehicleById(String vehicleId) throws Exception {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
        if(null == vehicle)
        {
            throw new Exception("Vehicle not found!");
        }
        return vehicle;
    }

    public List<Vehicle> getVehicleByModel(String model) throws Exception {
        List<Vehicle> vehicleList = vehicleRepository.findByModel(model);
        if(null == vehicleList || vehicleList.isEmpty())
        {
            throw new Exception("No vehicles found!");
        }
        return vehicleList;
    }

    public List<Vehicle> getVehicleByFuel(String fuel) throws Exception {
        List<Vehicle> vehicleList = vehicleRepository.findByFuel(fuel);
        if(null == vehicleList || vehicleList.isEmpty())
        {
            throw new Exception("No vehicles found!");
        }
        return vehicleList;
    }

    public List<Vehicle> getVehicleByTransmission(String transmission) throws Exception {
        List<Vehicle> vehicleList = vehicleRepository.findByTransmission(transmission);
        if(null == vehicleList || vehicleList.isEmpty())
        {
            throw new Exception("No vehicles found!");
        }
        return vehicleList;
    }

    public List<Vehicle> getVehicleByDealer(Dealer dealer) throws Exception {
        List<Vehicle> vehicleList = vehicleRepository.findByDealer(dealer);
        if(null == vehicleList || vehicleList.isEmpty())
        {
            throw new Exception("No vehicles found!");
        }
        return vehicleList;
    }


}
