package com.mercedes.mercedesio.controller;

import com.mercedes.mercedesio.model.entities.Vehicle;
import com.mercedes.mercedesio.repository.IVehicleRepository;
import com.mercedes.mercedesio.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class ListVehiclesBy {

    private final VehicleService vehicleService;

    public ListVehiclesBy(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> findAll(){
        List<Vehicle> vehicleList = new ArrayList<>();
        try
        {
            vehicleList = vehicleService.getAllVehicles();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return vehicleList;
    }


    @GetMapping(value = {"/id"})
    public Vehicle getVehicleById(@PathVariable("id") String id){
        Vehicle vehicle = new Vehicle();
        try {
            vehicle = vehicleService.getVehicleById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

}