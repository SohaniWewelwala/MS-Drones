package com.ms.drone.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MedicationDto {

    private String name;
    private int weight;
    private String code;
    private String droneSerialNumber;
}
