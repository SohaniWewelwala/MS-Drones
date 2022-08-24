package com.ms.drone.repository;

import com.ms.drone.model.Drone;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * DroneRepository interface for JPA.
 */
public interface DroneRepository extends CrudRepository<Drone, Long> {

    Optional<Drone> findBySerialNumber(String serialNumber);
}
