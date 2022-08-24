package com.ms.drone.repository;

import com.ms.drone.model.Drone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * DroneRepository interface for JPA.
 */
public interface DroneRepository extends CrudRepository<Drone, Long> {

    Optional<Drone> findBySerialNumber(String serialNumber);

    @Query(value = "SELECT * FROM drones WHERE drone_state = 'IDLE' OR drone_state = 'LOADING'", nativeQuery = true)
    Optional<List<Drone>> getAvailableDronesForLoading();
}
