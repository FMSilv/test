package com.mercedes.mercedesio.service;

import com.mercedes.mercedesio.common.ApplicationUtilities;
import com.mercedes.mercedesio.model.entities.Dealer;
import com.mercedes.mercedesio.model.entities.Vehicle;
import com.mercedes.mercedesio.repository.IDealerRepository;
import com.mercedes.mercedesio.repository.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealerService {

    private IDealerRepository dealerRepository;
    private IVehicleRepository vehicleRepository;
    private ApplicationUtilities applicationUtilities;

    @Autowired
    public DealerService(IDealerRepository dealerRepository, IVehicleRepository vehicleRepository, ApplicationUtilities applicationUtilities) {
        this.dealerRepository = dealerRepository;
        this.vehicleRepository = vehicleRepository;
        this.applicationUtilities = applicationUtilities;
    }

    public void saveEntity(Dealer dealer){
        dealerRepository.save(dealer);
    }

    public void saveEntityList(List<Dealer> dealerList){
        for(Dealer dealer : dealerList){
            dealerRepository.save(dealer);
        }
    }

    public Dealer getDealerById(String dealerId) throws Exception {
        Dealer dealer = dealerRepository.findById(dealerId).get();
        if(null == dealer)
        {
            throw new Exception("Dealer not found!");
        }
        return dealer;
    }

    public Dealer getClosestDealerToYourLocation(double latitude, double longitude, List<Dealer> dealerList) throws Exception {
        Dealer closestDealer = new Dealer();
        Double closestDistance = new Double(0);
        int count = 0;

        for(Dealer dealer : dealerList){
            Double distance = applicationUtilities.calculateDistanceBetweenToGeograficPoints(latitude, longitude, dealer.getLatitude(), dealer.getLongitude());

            if (count == 0)
            {
                closestDistance = distance;
            }
            else
            {
                int compareValue = distance.compareTo(closestDistance);
                if (compareValue < 0)
                {
                    closestDistance = distance;
                    closestDealer = dealer;
                }
            }
            count++;
        }
        return closestDealer;
    }

    public List<Dealer> getDealersWithVehicleAttributes(String model, String fuel, String transmission) throws Exception {
        List<Dealer> dealerList = new ArrayList<>();
        List<Vehicle> vehicleList = vehicleRepository.findByModelAndFuelAndTransmission(model, fuel, transmission);
        if(null == vehicleList || vehicleList.isEmpty())
        {
            throw new Exception("No dealer has a vehicle with the requested attributes.");
        }
        else
        {
            dealerList = selectDealersBasedOnVehiclesAttributes(dealerList, vehicleList);
        }

        return dealerList;
    }


    private List<Dealer> selectDealersBasedOnVehiclesAttributes(List<Dealer> dealerList, List<Vehicle> vehicleList) {
        boolean flagVehicleExistsInDealer = false;

        for(Vehicle vehicle : vehicleList)
        {
            for(Dealer dealer : dealerList){
                if(dealer.getId().equals(vehicle.getDealer().getId()))
                {
                    flagVehicleExistsInDealer = true;
                }
            }
            if(!flagVehicleExistsInDealer)
            {
                dealerList.add(vehicle.getDealer());
                flagVehicleExistsInDealer = false;
            }
        }
        return dealerList;
    }

}
