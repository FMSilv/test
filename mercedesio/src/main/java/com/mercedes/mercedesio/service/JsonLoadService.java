package com.mercedes.mercedesio.service;

import com.mercedes.mercedesio.common.ApplicationUtilities;
import com.mercedes.mercedesio.model.entities.*;
import com.mercedes.mercedesio.rules.JsonConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.List;


@Service
public class JsonLoadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonLoadService.class);

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private JsonConvert jsonConvert;
    @Autowired
    private DealerService dealerService;
    @Autowired
    private ApplicationUtilities applicationUtilities;
    @Autowired
    private VehicleService vehicleService;


    @PostConstruct
    public void init(){
        Resource resource = resourceLoader.getResource("classpath:"+"json/dataset.json");
        try
        {
/*            double distance1 = applicationUtilities.calculateDistanceBetweenToGeograficPoints(37.104404, -8.236308, 38.746721, -9.229837);
            System.out.println("distance1: "+distance1);
            double distance2 = applicationUtilities.calculateDistanceBetweenToGeograficPoints(38.746721, -9.229837, 37.104404, -8.236308);
            System.out.println("distance2: "+distance2);*/

            List<Dealer> dealerList = jsonConvert.getDealers(resource.getInputStream());
            List<Booking> bookingList = jsonConvert.getBookings(resource.getInputStream());

 /*           System.out.println("-----------------------------------");
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
            System.out.println("-----------------------------------");

            List<Booking> bookingList = jsonConvert.getBookings(resource.getInputStream());
            System.out.println("-----------------------------------");
            StringBuilder stringBuilder = new StringBuilder();
            for(Booking booking : bookingList){
                stringBuilder.append("Booking: "+"\n");
                stringBuilder.append("\tid: "+booking.getId()+"\n");
                stringBuilder.append("\tFirst Name: "+booking.getFirstName()+"\n");
                stringBuilder.append("\tLast Name :"+booking.getLastName()+"\n");
                stringBuilder.append("\tVehicle Id :"+booking.getVehicle().getId()+"\n");
                stringBuilder.append("\tPickup Date: "+booking.getPickupDate()+"\n");
                stringBuilder.append("\tCreated At: "+booking.getCreatedAt()+"\n");
            }
            System.out.println(stringBuilder.toString());
            System.out.println("-----------------------------------");
            System.out.println("-----------------------------------");

            Dealer dealer = dealerService.getDealerById("d4f4d287-1ad6-4968-a8ff-e9e0009ad5d1");
            for(Vehicle vehicle : dealer.getVehicleList()){
                System.out.println("[Id="+vehicle.getId()+"]" +
                        "[Model="+vehicle.getModel()+"]" +
                        "[Fuel="+vehicle.getFuel()+"]" +
                        "[Transmission="+vehicle.getTransmission()+"]" +
                        "[Availability={day:"+vehicle.getVehicleAvailabilityList().get(0).getDay()+",hour:"+vehicle.getVehicleAvailabilityList().get(0).getHour()+"};" +
                        "{day:"+vehicle.getVehicleAvailabilityList().get(1).getDay()+",hour:"+vehicle.getVehicleAvailabilityList().get(1).getHour()+"};" +
                        "{day:"+vehicle.getVehicleAvailabilityList().get(2).getDay()+",hour:"+vehicle.getVehicleAvailabilityList().get(2).getHour()+"};" +
                        "{day:"+vehicle.getVehicleAvailabilityList().get(3).getDay()+",hour:"+vehicle.getVehicleAvailabilityList().get(3).getHour()+"};");
            }
            System.out.println("-----------------------------------");
            System.out.println("-----------------------------------");*/

        }catch (Exception e){
            LOGGER.error("Resource not found: ", e);
            e.printStackTrace();
        }
    }

}
