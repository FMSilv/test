package com.mercedes.mercedesio.service;

import com.mercedes.mercedesio.model.entities.Dealer;
import com.mercedes.mercedesio.repository.IDealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerService {

    private IDealerRepository dealerRepository;

    @Autowired
    public DealerService(IDealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
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


}
