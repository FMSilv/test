package com.mercedes.mercedesio.repository;

import com.mercedes.mercedesio.model.entities.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDealerRepository extends JpaRepository<Dealer, String> {



}
