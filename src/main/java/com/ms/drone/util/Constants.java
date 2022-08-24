package com.ms.drone.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

public class Constants {

    public static final int MAXIMUM_WEIGHT_LIMIT = 500;

    /**
     * Enum for Drone models.
     */
    public enum DroneModel {

        LIGHTWEIGHT,
        MIDDLEWEIGHT,
        CRUISERWEIGHT,
        HEAVYWEIGHT
    }

    /**
     * Enum for Drone states.
     */
    public enum DroneState {

        IDLE,
        LOADING,
        LOADED,
        DELIVERING,
        DELIVERED,
        RETURNING
    }

    /**
     * Error messages to handle exceptions.
     */
    @RequiredArgsConstructor
    @Getter
    public enum ErrorMessages {

        ERROR_INVALID_BATTERY_CAPACITY("DM-001", "Invalid battery capacity.",
                "Battery capacity must be in range 1 - 100."),
        ERROR_INVALID_SERIAL_NUMBER("DM-002", "Invalid serial number.",
                "Serial number must be less than 100 characters."),
        ERROR_INVALID_WEIGHT_LIMIT("DM-003", "Invalid weight limit.", "Drone weight limit should not exceed 500g"),
        ERROR_INVALID_DRONE_MODEL("DM-004", "Invalid drone model.", "Drone model is invalid."),
        ERROR_NEGATIVE_WEIGHT_LIMIT("DM-005", "Invalid weight limit", "Weight limit cannot be negative."),
        ERROR_SERIAL_NUMBER_NOT_EXISTS("DM-006", "Invalid serial number", "The serial number does not exist."),
        ERROR_SERIAL_NUMBER_ALREADY_EXISTS("DM-007", "Invalid serial number", "Serial Number already exists.");

        private final String code;
        private final String message;
        private final String description;

        @Override
        public String toString() {

            return String.format("%s: %s", code, message);
        }
    }

    /**
     * Error codes for Forbidden Error Messages.
     */
    public enum ForbiddenErrorMessages {

    }

    /**
     * Error codes for Conflict Error Messages.
     */
    public enum ConflictErrorMessages {

    }

    /**
     * Error codes for Not Found Error Messages.
     */
    public enum NotFoundErrorMessages {
        DM_006
    }
}
