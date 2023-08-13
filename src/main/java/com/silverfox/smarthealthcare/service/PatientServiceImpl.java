package com.silverfox.smarthealthcare.service;

import com.silverfox.smarthealthcare.dto.PatientRequest;
import com.silverfox.smarthealthcare.dto.PatientResponse;
import com.silverfox.smarthealthcare.entity.Patient;
import com.silverfox.smarthealthcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PatientResponse> getPatientList() {

        List<PatientResponse> patientResponses = new ArrayList<>();

        for (Patient patient : patientRepository.findAll()) {
            PatientResponse patientResponse = modelMapper.map(patient, PatientResponse.class);
            patientResponses.add(patientResponse);
        }

        return patientResponses;

    }

    @Override
    public void savePatient(PatientRequest patientRequest) {

        Patient patient = modelMapper.map(patientRequest, Patient.class);
        patientRepository.save(patient);

    }
}
