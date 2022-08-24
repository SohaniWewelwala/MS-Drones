package com.ms.drone.controller;

import com.ms.drone.dto.DroneRegistrationDto;
import com.ms.drone.exception.DroneManagementClientException;
import com.ms.drone.exception.DroneManagementException;
import com.ms.drone.service.DroneService;
import com.ms.drone.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/drones")
@RequiredArgsConstructor
public class DispatchController {

    private final DroneService droneService;

    @PostMapping
    public ResponseEntity<Object> registerDrone(@RequestBody @Valid DroneRegistrationDto drone) {

        try {
            return ResponseEntity.ok(droneService.registerDrone(drone));
        } catch (DroneManagementClientException e) {
            return Utils.handleClientErrorResponse(e);
        }
    }

    @GetMapping(path = "/{serialNumber}")
    public ResponseEntity<Object> findBySerialNumber(@PathVariable String serialNumber) {

        try {
            return ResponseEntity.ok(droneService.getDrone(serialNumber));
        } catch (DroneManagementClientException e) {
            return Utils.handleClientErrorResponse(e);
        }
    }
}
