package com.ms.drone.util;

import com.ms.drone.dto.ErrorDto;
import com.ms.drone.util.Constants.ConflictErrorMessages;
import com.ms.drone.util.Constants.ForbiddenErrorMessages;
import com.ms.drone.util.Constants.NotFoundErrorMessages;
import com.ms.drone.exception.DroneManagementClientException;
import com.ms.drone.exception.DroneManagementException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utility class containing utility functions for Drone Management.
 */
public class Utils {

    /**
     * Throw an DroneManagementClientException upon client side error in Drone Management.
     *
     * @param error The error enum.
     * @param data  The error message data.
     * @return DroneManagementClientException.
     */
    public static DroneManagementClientException handleException(Constants.ErrorMessages error, String... data) {

        String description = error.getDescription();
        if (ArrayUtils.isNotEmpty(data)) {
            description = String.format(description, data);
        }
        return new DroneManagementClientException(error.getMessage(), description, error.getCode());
    }

    /**
     * Handles client exceptions for Drone Management.
     * @param e The drone management exception.
     * @return The response of the error.
     */
    public static ResponseEntity<Object> handleClientErrorResponse(DroneManagementClientException e) {

        ErrorDto error = new ErrorDto();
        BeanUtils.copyProperties(e, error);
        if (isNotFoundError(e)) {
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } else if (isConflictError(e)) {
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        } else if (isForbiddenError(e)) {
            return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the server exceptions for drone management.
     * @param e The drone management exception.
     * @return The response of the error.
     */
    public static ResponseEntity<Object> handleServerErrorException(DroneManagementException e) {

        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Throw an DroneManagementClientException upon client side error in Drone Management.
     *
     * @param error     The error enum.
     * @param throwable The throwable object.
     * @param data      The error message data.
     * @return DroneManagementClientException.
     */
    public static DroneManagementClientException handleException(Constants.ErrorMessages error,
                                                                 Throwable throwable, String... data) {

        String description = error.getDescription();
        if (ArrayUtils.isNotEmpty(data)) {
            description = String.format(description, data);
        }
        return new DroneManagementClientException(error.getCode(), error.getMessage(), description, throwable);
    }

    /**
     * Checks the exception key code and returns true if it is a conflict error.
     *
     * @param e The drone management exception.
     * @return If the exception is thrown due to conflict error it returns true, else false.
     */
    private static boolean isConflictError(DroneManagementException e) {

        String code = e.getCode().replace("-", "_");
        return EnumUtils.isValidEnum(ConflictErrorMessages.class, code);
    }

    /**
     * Checks the exception key code and returns true if it is a forbidden error.
     *
     * @param e The drone management exception.
     * @return If the exception is thrown due to forbidden error it returns true, else false.
     */
    private static boolean isForbiddenError(DroneManagementException e) {

        String code = e.getCode().replace("-", "_");
        return EnumUtils.isValidEnum(ForbiddenErrorMessages.class, code);
    }

    /**
     * Checks the exception key code and returns true if it is a not-found error.
     *
     * @param e The drone management exception.
     * @return If the exception is thrown due to not-found error it returns true, else false.
     */
    private static boolean isNotFoundError(DroneManagementException e) {

        String code = e.getCode().replace("-", "_");
        return EnumUtils.isValidEnum(NotFoundErrorMessages.class, code);
    }
}
