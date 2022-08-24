package com.ms.drone.service.impl;

import com.ms.drone.dto.DroneRegistrationDto;
import com.ms.drone.exception.DroneManagementClientException;
import com.ms.drone.model.Drone;
import com.ms.drone.repository.DroneRepository;
import com.ms.drone.service.DroneService;
import com.ms.drone.util.Constants;
import com.ms.drone.util.Utils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.ms.drone.util.Constants.ErrorMessages.ERROR_INVALID_BATTERY_CAPACITY;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_INVALID_SERIAL_NUMBER;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_INVALID_WEIGHT_LIMIT;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_INVALID_DRONE_MODEL;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_NEGATIVE_WEIGHT_LIMIT;
import static com.ms.drone.util.Constants.MAXIMUM_WEIGHT_LIMIT;
import static com.ms.drone.util.Constants.DroneState.IDLE;
import static com.ms.drone.util.Constants.DroneModel;
import static com.ms.drone.util.Constants.DroneModel.LIGHTWEIGHT;
import static com.ms.drone.util.Constants.DroneModel.MIDDLEWEIGHT;
import static com.ms.drone.util.Constants.DroneModel.CRUISERWEIGHT;
import static com.ms.drone.util.Constants.DroneModel.HEAVYWEIGHT;

@AllArgsConstructor
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public Drone registerDrone(DroneRegistrationDto drone) throws DroneManagementClientException {

        if (drone.getBatteryCapacity() > 100 || drone.getBatteryCapacity() <= 0) {
            throw Utils.handleException(ERROR_INVALID_BATTERY_CAPACITY);
        }
        if(drone.getSerialNumber().length() > 100 ){
            throw Utils.handleException(ERROR_INVALID_SERIAL_NUMBER);
        }
        if(drone.getWeightLimit() < 0){
            throw Utils.handleException(ERROR_NEGATIVE_WEIGHT_LIMIT);
        }
        if (drone.getWeightLimit() > MAXIMUM_WEIGHT_LIMIT) {
            throw Utils.handleException(ERROR_INVALID_WEIGHT_LIMIT);
        }
        String droneModel = drone.getDroneModel().toUpperCase();
        if (!StringUtils.equalsIgnoreCase(droneModel, LIGHTWEIGHT.name()) &&
                !StringUtils.equalsIgnoreCase(droneModel, MIDDLEWEIGHT.name()) &&
                !StringUtils.equalsIgnoreCase(droneModel, HEAVYWEIGHT.name()) &&
                !StringUtils.equalsIgnoreCase(droneModel, CRUISERWEIGHT.name())) {
            throw Utils.handleException(ERROR_INVALID_DRONE_MODEL);
        }

        Drone newDrone = new Drone();
        //copy bean properties.
        BeanUtils.copyProperties(drone, newDrone);
        //initially drone is in IDLE state.
        newDrone.setDroneModel(DroneModel.valueOf(droneModel));
        newDrone.setDroneState(IDLE);
        //save in the database
        droneRepository.save(newDrone);

        return droneRepository.save(newDrone);
    }
}
