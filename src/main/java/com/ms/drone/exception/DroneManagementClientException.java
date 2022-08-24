package com.ms.drone.exception;

/**
 * This class is to handle the client side errors in Drone Management.
 */
public class DroneManagementClientException extends DroneManagementException{

    public DroneManagementClientException(String message, String description, String errorCode){

        super(message, description, errorCode);
    }

    public DroneManagementClientException(String message, String description, String errorCode, Throwable cause){

        super(message, description, errorCode, cause);
    }
}
