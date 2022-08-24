package com.ms.drone.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * DTO for errors.
 */
@Data
@Component
public class ErrorDto {

    private String code;
    private String message;
    private String description;
}
