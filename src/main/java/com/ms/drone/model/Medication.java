package com.ms.drone.model;

import lombok.Data;

/**
 * Medication model.
 */
@Data
public class Medication {

    private String name;
    private int weight;
    private String code;
    private String image;
}
