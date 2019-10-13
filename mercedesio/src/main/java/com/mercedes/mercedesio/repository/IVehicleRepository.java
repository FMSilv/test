package com.mercedes.mercedesio.repository;

import com.mercedes.mercedesio.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, String> {



}
