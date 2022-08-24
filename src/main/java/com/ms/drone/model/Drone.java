package com.ms.drone.model;

import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import static com.ms.drone.util.Constants.DroneModel;
import static com.ms.drone.util.Constants.DroneState;

/**
 * Drone model.
 */
@Entity
@Data
@Table(name = "drones")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_id")
    private Long id;

    @Column(name = "drone_serial_number", length = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "drone_model")
    private DroneModel droneModel;

    @Column(name = "drone_weight_limit")
    private Integer weightLimit;

    @Column(name = "drone_battery_capacity")
    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "drone_state")
    private DroneState droneState;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drone")
    private List<Medication> medicationList;
}
