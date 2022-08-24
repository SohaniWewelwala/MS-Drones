package com.ms.drone.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Base exception class for Drone Management.
 */
@Getter
@Setter
public class DroneManagementException extends Exception{

    private String code;
    private String description;

    public DroneManagementException(String message, String code){

        super(message);
        this.code = code;
    }

    public DroneManagementException(String message, String code, Throwable cause){

        super(message, cause);
        this.code = code;
    }

    public DroneManagementException(String message, String description, String code){

        super(message);
        this.code = code;
        this.description = description;
    }

    public DroneManagementException(String message, String description, String errorCode, Throwable cause){

        super(message, cause);
        this.code = errorCode;
        this.description = description;
    }
}
