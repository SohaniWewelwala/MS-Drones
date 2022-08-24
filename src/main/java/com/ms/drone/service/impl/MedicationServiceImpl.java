package com.ms.drone.service.impl;

import com.ms.drone.dto.MedicationDto;
import com.ms.drone.exception.DroneManagementClientException;
import com.ms.drone.model.Drone;
import com.ms.drone.model.Medication;
import com.ms.drone.repository.DroneRepository;
import com.ms.drone.repository.MedicationRepository;
import com.ms.drone.service.MedicationService;
import com.ms.drone.util.Utils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ms.drone.util.Constants.DroneState.LOADED;
import static com.ms.drone.util.Constants.DroneState.LOADING;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_BATTERY_LEVEL_LOW;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_DRONE_NOT_AVAILABLE_FOR_LOADING;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_DRONE_WEIGHT_LIMIT_EXCEEDED;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_INVALID_MEDICATION_CODE;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_INVALID_MEDICATION_NAME;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_MEDICATION_WEIGHT_NOT_NEGATIVE;
import static com.ms.drone.util.Constants.ErrorMessages.ERROR_SERIAL_NUMBER_NOT_EXISTS;
import static com.ms.drone.util.Constants.MINIMUM_BATTERY_CAPACITY;
import static com.ms.drone.util.Constants.REGEX;
import static com.ms.drone.util.Constants.DroneState.IDLE;

@AllArgsConstructor
@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;
    private final DroneRepository droneRepository;

    @Transactional
    @Override
    public Medication addMedication(MedicationDto medication, Byte[] img) throws DroneManagementClientException {

        if (!medication.getName().matches(REGEX)) {
            throw Utils.handleException(ERROR_INVALID_MEDICATION_NAME);
        }
        if (!medication.getCode().matches(REGEX)) {
            throw Utils.handleException(ERROR_INVALID_MEDICATION_CODE);
        }
        if(medication.getWeight() < 0){
            throw Utils.handleException(ERROR_MEDICATION_WEIGHT_NOT_NEGATIVE);
        }
        Optional<Drone> opt = droneRepository.findBySerialNumber(medication.getDroneSerialNumber());
        Drone drone = null;
        if (opt.isPresent()) {
            drone = opt.get();
        }
        if (drone == null) {
            throw Utils.handleException(ERROR_SERIAL_NUMBER_NOT_EXISTS);
        }
        if (!StringUtils.equalsIgnoreCase(drone.getDroneState().name(), IDLE.name()) &&
                !StringUtils.equalsIgnoreCase(drone.getDroneState().name(), LOADING.name())) {
            throw Utils.handleException(ERROR_DRONE_NOT_AVAILABLE_FOR_LOADING);
        }
        if(drone.getBatteryCapacity() < MINIMUM_BATTERY_CAPACITY){
            throw Utils.handleException(ERROR_BATTERY_LEVEL_LOW);
        }
        /**
         * we will subtract the weight limit of drone until it gets to 0. If the drone weight limit goes negative, it cannot
         * load more medicine.
         * */
        int weightDiff = drone.getWeightLimit() - medication.getWeight();
        if (weightDiff < 0) {
            throw Utils.handleException(ERROR_DRONE_WEIGHT_LIMIT_EXCEEDED);
        }
        /** If we can add the medications without exceeding limit, and the weight limit is greater than 0 after that the drone state will be LOADING.
         * If the weight limit is 0 them the drone is fully LOADED.
         * */
        drone.setWeightLimit(weightDiff);
        drone.setDroneState(weightDiff > 0 ? LOADING : LOADED);
        droneRepository.save(drone);

        Medication newMedication = new Medication();
        //copy properties.
        BeanUtils.copyProperties(medication,newMedication, "drone");
        newMedication.setDrone(drone);
        newMedication.setImage(img);
        medicationRepository.save(newMedication);

        return newMedication;
    }
}
