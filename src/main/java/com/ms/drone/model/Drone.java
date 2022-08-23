package com.ms.drone.model;

import lombok.Data;

import static com.ms.drone.util.Constants.DroneModel;
import static com.ms.drone.util.Constants.DroneState;

/**
 * Drone model.
 */
@Data
public class Drone {

    private String serialNumber;
    private DroneModel droneModel;
    private int weightLimit;
    private int batteryCapacity;
    private DroneState droneState;

}
