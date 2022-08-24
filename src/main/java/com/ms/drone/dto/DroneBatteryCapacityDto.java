package com.ms.drone.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DroneBatteryCapacityDto {

    private String serialNumber;
    private int batteryCapacity;
}
