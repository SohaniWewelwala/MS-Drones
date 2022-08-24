package com.ms.drone.service;

import com.ms.drone.dto.DroneRegistrationDto;
import com.ms.drone.exception.DroneManagementClientException;
import com.ms.drone.model.Drone;

import java.util.List;

public interface DroneService {

    Drone registerDrone(DroneRegistrationDto drone) throws DroneManagementClientException;
    Drone getDrone(String droneSerialNumber) throws DroneManagementClientException;
    List<Drone> getDronesList();
}
