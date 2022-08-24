package com.ms.drone.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Medication model.
 */
@Entity
@Data
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medication_id")
    private Long id;

    @Column(name = "medication_name")
    private String name;

    @Column(name = "medication_weight")
    private Integer weight;

    @Column(name = "medication_code")
    private String code;

    @Lob
    @Column(name = "medication_img")
    private Byte[] image;

    @ManyToOne()
    private Drone drone;
}
