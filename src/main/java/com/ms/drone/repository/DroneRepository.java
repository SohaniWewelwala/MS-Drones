package com.ms.drone.repository;

import com.ms.drone.model.Drone;
import org.springframework.data.repository.CrudRepository;

/**
 * DroneRepository interface for JPA.
 */
public interface DroneRepository extends CrudRepository<Drone, Long> {

}
