package com.mercedes.mercedesio.service;

import com.mercedes.mercedesio.model.entities.Dealer;
import com.mercedes.mercedesio.model.entities.DealerCloseTime;
import com.mercedes.mercedesio.model.entities.Vehicle;
import com.mercedes.mercedesio.model.entities.VehicleAvailability;
import com.mercedes.mercedesio.rules.JsonConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class JsonLoadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonLoadService.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JsonConvert jsonConvert;


    @PostConstruct
    public void init(){
        Resource resource = resourceLoader.getResource("classpath:"+"json/dataset.json");
        try
        {
            List<Dealer> dealerList = jsonConvert.getDealers(resource.getInputStream());

            for(Dealer dealer : dealerList){
                System.out.println("-----------------------------------");
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append("Dealer: "+"\n");
                stringBuilder.append("\tid: "+dealer.getId()+"\n");
                stringBuilder.append("\tname: "+dealer.getName()+"\n");
                stringBuilder.append("\tlatitude: "+dealer.getLatitude()+"\n");
                stringBuilder.append("\tlongitude: "+dealer.getLongitude());
                System.out.println(stringBuilder.toString());

                stringBuilder = new StringBuilder();
                stringBuilder.append("\tClose Time: [");
                for(DealerCloseTime dealerCloseTime : dealer.getDealerCloseTimeList()){
                    stringBuilder.append(dealerCloseTime.getDay()+";");
                }
                stringBuilder.append("]");
                System.out.println(stringBuilder.toString());

                stringBuilder = new StringBuilder();
                for(Vehicle vehicle : dealer.getVehicleList()){
                    stringBuilder.append("\tVehicle: "+"\n");
                    stringBuilder.append("\t\tid: "+vehicle.getId()+"\n");
                    stringBuilder.append("\t\tmodel: "+vehicle.getModel()+"\n");
                    stringBuilder.append("\t\tfuel: "+vehicle.getFuel()+"\n");
                    stringBuilder.append("\t\ttransmission: "+vehicle.getTransmission()+"\n");
                    stringBuilder.append("\t\tavailability: \n");
                    for(VehicleAvailability vehicleAvailability : vehicle.getVehicleAvailabilityList()){
                        stringBuilder.append("\t\t\tDay: "+vehicleAvailability.getDay()+" | Hour: "+vehicleAvailability.getHour()+"\n");
                    }
                }
                System.out.println(stringBuilder.toString());

            }
            System.out.println("-----------------------------------");


        }catch (Exception e){
            LOGGER.error("Resource not found: ", e);
            e.printStackTrace();
        }
    }

}
