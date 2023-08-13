package com.silverfox.smarthealthcare.repository;

import com.silverfox.smarthealthcare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
