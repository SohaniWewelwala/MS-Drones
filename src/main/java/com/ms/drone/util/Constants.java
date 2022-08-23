package com.ms.drone.util;

public class Constants {

    /**
     * Enum for Drone models.
     */
    public enum DroneModel{

        LIGHTWEIGHT,
        MIDDLEWEIGHT,
        CRUISERWEIGHT,
        HEAVYWEIGHT
    }

    /**
     * Enum for Drone states.
     */
    public enum DroneState{

        IDLE,
        LOADING,
        LOADED,
        DELIVERING,
        DELIVERED,
        RETURNING
    }
}
