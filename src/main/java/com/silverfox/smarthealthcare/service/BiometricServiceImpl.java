package com.silverfox.smarthealthcare.service;

import com.silverfox.smarthealthcare.dto.BiometricRequest;
import com.silverfox.smarthealthcare.entity.Biometric;
import com.silverfox.smarthealthcare.entity.Rehabilitation;
import com.silverfox.smarthealthcare.enums.RehabilitationStatus;
import com.silverfox.smarthealthcare.exception.RehabilitationNotFoundException;
import com.silverfox.smarthealthcare.exception.RehabilitationStatusException;
import com.silverfox.smarthealthcare.repository.BiometricRepository;
import com.silverfox.smarthealthcare.repository.RehabilitationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BiometricServiceImpl implements BiometricService{

    private final RehabilitationRepository rehabilitationRepository;
    private final BiometricRepository biometricRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveBiometric(BiometricRequest biometricRequest) {

        Rehabilitation rehabilitation = rehabilitationRepository.findById(biometricRequest.getRehabilitationId())
                .orElseThrow(() -> new RehabilitationNotFoundException(biometricRequest.getRehabilitationId()));

        if(rehabilitation.getRehabilitationStatus() != RehabilitationStatus.ING) {
            throw new RehabilitationStatusException("현재 진행중인 재활에 대해서만 생체데이터를 삽입할 수 있습니다.");
        }

        Biometric biometric = modelMapper.map(biometricRequest, Biometric.class);

        biometric.setRehabilitation(rehabilitation);
        biometricRepository.save(biometric);
    }
}
