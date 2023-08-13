package com.silverfox.smarthealthcare.service;

import com.silverfox.smarthealthcare.dto.PatientRequest;
import com.silverfox.smarthealthcare.dto.PatientResponse;

import java.util.List;

public interface PatientService {

    List<PatientResponse> getPatientList();
    void savePatient(PatientRequest patientRequest);
}
