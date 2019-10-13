package com.mercedes.mercedesio.service;

import com.mercedes.mercedesio.model.entities.Vehicle;
import com.mercedes.mercedesio.repository.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
