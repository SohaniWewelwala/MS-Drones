package com.ms.drone.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * DTO for Drone registration.
 */
@Data
@Component
public class DroneRegistrationDto {

    private String serialNumber;
    private String droneModel;
    private int weightLimit;
    private int batteryCapacity;
}
