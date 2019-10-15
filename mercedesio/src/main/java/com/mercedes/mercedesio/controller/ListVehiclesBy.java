package com.mercedes.mercedesio.controller;

import com.mercedes.mercedesio.model.entities.Vehicle;
import com.mercedes.mercedesio.repository.IVehicleRepository;
import com.mercedes.mercedesio.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/vehicle")
public class ListVehiclesBy {

    private final VehicleService vehicleService;

    public ListVehiclesBy(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(method = RequestMethod.GET )
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

    @GetMapping
    public Vehicle getVehicleByIdParam(@RequestParam(value = "id") String id){
        Vehicle vehicle = new Vehicle();
        try {
            vehicle = vehicleService.getVehicleById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    @GetMapping(value = {"/{id}"})
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