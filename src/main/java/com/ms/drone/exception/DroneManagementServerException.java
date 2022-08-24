package com.ms.drone.exception;

/**
 * This class is to handle the server side errors of the Drone Management.
 */
public class DroneManagementServerException extends DroneManagementException{

    public DroneManagementServerException (String message, String errorCode){

        super(message, errorCode);
    }

    public DroneManagementServerException(String message, String errorCode, Throwable cause){

        super(message, errorCode, cause);
    }

    public DroneManagementServerException(String message, String description, String errorCode, Throwable cause){

        super(message, description, errorCode, cause);
    }
}
