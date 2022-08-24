package com.ms.drone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.drone.dto.DroneRegistrationDto;
import com.ms.drone.dto.MedicationDto;
import com.ms.drone.exception.DroneManagementClientException;
import com.ms.drone.exception.DroneManagementException;
import com.ms.drone.model.Drone;
import com.ms.drone.service.DroneService;
import com.ms.drone.service.MedicationService;
import com.ms.drone.util.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import static com.ms.drone.util.Constants.ErrorMessages.ERROR_INVALID_IMAGE_FILE;

@RestController
@RequestMapping("/api/v1/drones")
@RequiredArgsConstructor
public class DispatchController {

    private final DroneService droneService;
    private final MedicationService medicationService;

    @PostMapping
    public ResponseEntity<Object> registerDrone(@RequestBody @Valid DroneRegistrationDto drone) {

        try {
            return ResponseEntity.ok(droneService.registerDrone(drone));
        } catch (DroneManagementClientException e) {
            return Utils.handleClientErrorResponse(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Drone>> getDronesList() {

        return ResponseEntity.ok(droneService.getDronesList());
    }

    @GetMapping(path = "/{serialNumber}")
    public ResponseEntity<Object> findBySerialNumber(@PathVariable String serialNumber) {

        try {
            return ResponseEntity.ok(droneService.getDrone(serialNumber));
        } catch (DroneManagementClientException e) {
            return Utils.handleClientErrorResponse(e);
        }
    }

    @GetMapping(path = "/available-drones-for-loading")
    public ResponseEntity<Object> getDronesAvailableForLoading() {

        try {
            return ResponseEntity.ok(droneService.getAvailableDronesForLoading());
        } catch (DroneManagementClientException e) {
            return Utils.handleClientErrorResponse(e);
        }
    }

    @PostMapping(path = "/add-medication", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> addMedication(@RequestPart("file") MultipartFile file,
                                                @RequestPart("medication") String medication) throws IOException {

        try {
            if (file == null) {
                Utils.handleClientErrorResponse(Utils.handleException(ERROR_INVALID_IMAGE_FILE));
            }
            //Image as a byte array.
            Byte[] img = ArrayUtils.toObject(file.getBytes());
            //ObjectMapper to change json to java object.
            ObjectMapper objectMapper = new ObjectMapper();
            MedicationDto medicationDto = objectMapper.readValue(medication, MedicationDto.class);
            //calling addMedicationMethod
            return ResponseEntity.ok(medicationService.addMedication(medicationDto, img));
        } catch (DroneManagementClientException e) {
            return Utils.handleClientErrorResponse(e);
        }
    }

    @GetMapping(path = "/battery-capacity/{serialNumber}")
    public ResponseEntity<Object> getBatteryCapacityOfADrone(@PathVariable String serialNumber) {

        try {
            return ResponseEntity.ok(droneService.getBatteryCapacityOfADrone(serialNumber));
        } catch (DroneManagementClientException e) {
            return Utils.handleClientErrorResponse(e);
        }
    }
}
