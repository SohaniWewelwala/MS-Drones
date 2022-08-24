package com.ms.drone.service;

import com.ms.drone.dto.DroneRegistrationDto;
import com.ms.drone.exception.DroneManagementClientException;
import com.ms.drone.model.Drone;

public interface DroneService {

    Drone registerDrone(DroneRegistrationDto drone) throws DroneManagementClientException;
}
