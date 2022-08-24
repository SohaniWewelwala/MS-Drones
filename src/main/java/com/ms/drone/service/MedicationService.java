package com.ms.drone.service;

import com.ms.drone.dto.MedicationDto;
import com.ms.drone.exception.DroneManagementClientException;
import com.ms.drone.model.Medication;

import java.util.List;

public interface MedicationService {

    /**
     * Add medication.
     *
     * @param medication The MedicationDTO.
     * @return The Medication Object.
     * @throws DroneManagementClientException Throws an exception if an error occurs while adding medication.
     */
    Medication addMedication(MedicationDto medication, Byte[] img) throws DroneManagementClientException;
}
