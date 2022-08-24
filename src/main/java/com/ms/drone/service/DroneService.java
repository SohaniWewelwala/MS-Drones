package com.ms.drone.service;

import com.ms.drone.dto.DroneRegistrationDto;
import com.ms.drone.exception.DroneManagementClientException;
import com.ms.drone.model.Drone;

import java.util.List;

public interface DroneService {

    /**
     * Register a drone in the system.
     *
     * @param drone The DroneDTO.
     * @return A Drone object.
     * @throws DroneManagementClientException Throws an exception if an error occurs while registering a drone.
     */
    Drone registerDrone(DroneRegistrationDto drone) throws DroneManagementClientException;

    /**
     * Get drone using serial number.
     *
     * @param droneSerialNumber The serial number of a drone.
     * @return The drone with the corresponding serial number.
     * @throws DroneManagementClientException Throws an exception if an error occurs while getting a drone using serial number.
     */
    Drone getDrone(String droneSerialNumber) throws DroneManagementClientException;

    /**
     * Get all the drones available in the system.
     *
     * @return A list containing the drones.
     */
    List<Drone> getDronesList();

    /**
     * Get available drones for loading. In this case, all the drones are in IDLE state are available for LOADING.
     *
     * @return A list of drones available for LOADING.
     * @throws DroneManagementClientException Throws an exception if an error occurs while getting all the available drones for LOADING.
     */
    List<Drone> getAvailableDronesForLoading() throws DroneManagementClientException;
}
