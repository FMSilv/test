package com.mercedes.mercedesio.rules;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercedes.mercedesio.common.Constantes;
import com.mercedes.mercedesio.model.entities.*;
import com.mercedes.mercedesio.service.BookingService;
import com.mercedes.mercedesio.service.DealerService;
import com.mercedes.mercedesio.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Component
public class JsonConvert {

    private DealerService dealerService;
    private VehicleService vehicleService;
    private BookingService bookingService;

    @Autowired
    public JsonConvert(DealerService dealerService, VehicleService vehicleService, BookingService bookingService) {
        this.dealerService = dealerService;
        this.vehicleService = vehicleService;
        this.bookingService = bookingService;
    }

    public List<Dealer> getDealers(InputStream jsonInputStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonInputStream);
        List<Dealer> dealerList = new ArrayList<>();

        JsonNode objectDealers = root.get(Constantes.DEALERS);
        for(JsonNode dealersNode : objectDealers){
            Dealer dealer = new Dealer();
            dealer.setId(dealersNode.get(Constantes.ID).asText());
            dealer.setName(dealersNode.get(Constantes.D_NAME).asText());
            dealer.setLatitude(Double.valueOf(dealersNode.get(Constantes.D_LATITUDE).asText()));
            dealer.setLongitude(Double.valueOf(dealersNode.get(Constantes.D_LONGITUDE).asText()));
            dealer.setDealerCloseTimeList(storeCloseTimeInfo(dealersNode));
            dealer.setVehicleList(storeVehiclesInfo(dealersNode));
            dealerList.add(dealer);
            dealerService.saveEntity(dealer);

        }
        return dealerList;
    }

    public List<Booking> getBookings(InputStream jsonInputStream) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonInputStream);
        List<Booking> bookingList = new ArrayList<>();

        JsonNode objectBookings = root.get(Constantes.BOOKINGS);
        for(JsonNode bookingsNode : objectBookings){
            Booking booking = new Booking();
            booking.setId(bookingsNode.get(Constantes.ID).asText());
            booking.setFirstName(bookingsNode.get(Constantes.B_FIRSTNAME).asText());
            booking.setLastName(bookingsNode.get(Constantes.B_LASTNAME).asText());
            booking.setVehicle(vehicleService.getVehicleById(bookingsNode.get(Constantes.B_VEHICLEID).asText()));
            booking.setPickupDate(bookingsNode.get(Constantes.B_PICKUPDATE).asText());
            booking.setCreatedAt(bookingsNode.get(Constantes.B_CREATEDAT).asText());
            bookingList.add(booking);
            bookingService.saveEntity(booking);
        }
        return bookingList;
    }

    private List<DealerCloseTime> storeCloseTimeInfo(JsonNode dealersNode){
        List<DealerCloseTime> listDealerCloseTimes = new ArrayList<>();
        if(dealersNode.get(Constantes.D_CLOSED).isArray()){
           for(JsonNode day : dealersNode.get(Constantes.D_CLOSED)){
               DealerCloseTime dealerCloseTime = new DealerCloseTime();
               dealerCloseTime.setDay(day.asText());
               listDealerCloseTimes.add(dealerCloseTime);
           }
        }
        return listDealerCloseTimes;
    }

    private List<Vehicle> storeVehiclesInfo(JsonNode dealersNode){
        List<Vehicle> vehicleList = new ArrayList<>();

        if(dealersNode.get(Constantes.VEHICLES).isArray()){
            for(JsonNode vehicleNode : dealersNode.get(Constantes.VEHICLES)){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(vehicleNode.get(Constantes.ID).asText());
                vehicle.setModel(vehicleNode.get(Constantes.V_MODEL).asText());
                vehicle.setFuel(vehicleNode.get(Constantes.V_FUEL).asText());
                vehicle.setTransmission(vehicleNode.get(Constantes.V_TRANSMISSION).asText());
                vehicle.setVehicleAvailabilityList(storeVehicleAvailability(vehicleNode.get(Constantes.V_AVAILABILITY)));
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }


    private List<VehicleAvailability> storeVehicleAvailability(JsonNode availabilityNode){
        List<VehicleAvailability> vehicleAvailabilityList = new ArrayList<>();

        if(availabilityNode.isObject()){
            Iterator<String> stringIterator = availabilityNode.fieldNames();

            while(stringIterator.hasNext()){
                String availabilityKey = stringIterator.next();
                if(availabilityNode.get(availabilityKey).isArray()){
                    for(JsonNode hour : availabilityNode.get(availabilityKey)){
                        VehicleAvailability vehicleAvailability = new VehicleAvailability();
                        vehicleAvailability.setDay(availabilityKey);
                        vehicleAvailability.setHour(hour.asText());
                        vehicleAvailabilityList.add(vehicleAvailability);
                    }
                }
            }
        }
        return vehicleAvailabilityList;
    }

}
