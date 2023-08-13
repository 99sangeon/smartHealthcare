package com.silverfox.smarthealthcare.service;

import com.silverfox.smarthealthcare.dto.*;
import com.silverfox.smarthealthcare.entity.BiometricAvg;
import com.silverfox.smarthealthcare.entity.Patient;
import com.silverfox.smarthealthcare.entity.Rehabilitation;
import com.silverfox.smarthealthcare.enums.RehabilitationStatus;
import com.silverfox.smarthealthcare.exception.PatientNotFoundException;
import com.silverfox.smarthealthcare.exception.RehabilitationNotFoundException;
import com.silverfox.smarthealthcare.repository.PatientRepository;
import com.silverfox.smarthealthcare.repository.RehabilitationRepository;
import com.silverfox.smarthealthcare.repository.custom.BiometricRepositoryCustom;
import com.silverfox.smarthealthcare.repository.custom.RehabilitationRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RehabilitationServiceImpl implements RehabilitationService{

    private final RehabilitationRepository rehabilitationRepository;
    private final RehabilitationRepositoryCustom rehabilitationRepositoryCustom;
    private final BiometricRepositoryCustom biometricRepositoryCustom;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RehabilitationSimpleResponse> getRehabilitationList() {

        List<RehabilitationSimpleResponse> rehabilitations = new ArrayList<>();

        for (Rehabilitation rehabilitation : rehabilitationRepositoryCustom.findRehabilitationList()) {

            RehabilitationSimpleResponse rehabilitationResponse = modelMapper.map(rehabilitation, RehabilitationSimpleResponse.class);

            Patient patient = rehabilitation.getPatient();
            PatientResponse patientResponse = modelMapper.map(patient, PatientResponse.class);

            rehabilitationResponse.setPatientResponse(patientResponse);

            rehabilitations.add(rehabilitationResponse);
        }

        return rehabilitations;
    }

    @Override
    public RehabilitationResponse getRehabilitation(Long id) {

        Rehabilitation rehabilitation = rehabilitationRepositoryCustom.findRehabilitation(id);

        RehabilitationResponse rehabilitationResponse = modelMapper.map(rehabilitation, RehabilitationResponse.class);

        return rehabilitationResponse;
    }

    @Override
    public Long saveRehabilitation(RehabilitationInitialRequest initialRequest) {

        Patient patient = patientRepository.findById(initialRequest.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException(initialRequest.getPatientId()));

        Rehabilitation rehabilitation = modelMapper.map(initialRequest, Rehabilitation.class);

        rehabilitation.setPatient(patient);
        rehabilitation.setRehabilitationStatus(RehabilitationStatus.BEFORE);
        rehabilitationRepository.save(rehabilitation);

        return rehabilitation.getId();
    }

    @Override
    public void rehabilitationStart(Long id) {

        Rehabilitation rehabilitation = rehabilitationRepository.findById(id)
                .orElseThrow(() -> new RehabilitationNotFoundException(id));

        rehabilitation.setStartTime(LocalDateTime.now());
        rehabilitation.setRehabilitationStatus(RehabilitationStatus.ING);

    }

    @Override
    public void rehabilitationEnd(Long id,  RehabilitationEndRequest endRequest) {

        Rehabilitation rehabilitation =  rehabilitationRepository.findById(id)
                .orElseThrow(() -> new RehabilitationNotFoundException(id));

        Patient patient = patientRepository.findById(rehabilitation.getPatient().getId())
                .orElseThrow(() -> new RehabilitationNotFoundException(id));

        BiometricAvg biometricAvg = biometricRepositoryCustom.findBiometricAvg(rehabilitation);
        rehabilitation.setBiometricAvg(biometricAvg);

        rehabilitation.setEndTime(LocalDateTime.now());
        rehabilitation.setRehabilitationStatus(RehabilitationStatus.AFTER);

        rehabilitation.setRemainingTime(endRequest.getRemainingTime());
        rehabilitation.setActualTime(rehabilitation.getGoalTime() - endRequest.getRemainingTime());
        rehabilitation.setSlope(endRequest.getSlope());
        rehabilitation.setTravelRange(endRequest.getTravelRange());
        rehabilitation.setSpeed(rehabilitation.getTravelRange() / rehabilitation.getActualTime());
        rehabilitation.setConsumedCalories(calculateConsumedCalories(rehabilitation.getSpeed(), rehabilitation.getSlope(), patient.getWeight()));

    }


    private float calculateConsumedCalories(float speed, float slope, float weight) {

        float vo2 = (float) (3.5 + (0.1 * speed) + (1.8 * speed * slope));
        float consumedCalories = (vo2 * weight / 200);

        return consumedCalories;
    }

}