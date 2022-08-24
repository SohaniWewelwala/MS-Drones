package com.ms.drone.repository;

import com.ms.drone.model.Medication;
import org.springframework.data.repository.CrudRepository;

/**
 * MedicationRepository interface for JPA.
 */
public interface MedicationRepository extends CrudRepository<Medication, Long> {

}
