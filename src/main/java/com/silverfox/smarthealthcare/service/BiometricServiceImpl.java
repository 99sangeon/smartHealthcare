package com.silverfox.smarthealthcare.service;

import com.silverfox.smarthealthcare.dto.BiometricRequest;
import com.silverfox.smarthealthcare.entity.Biometric;
import com.silverfox.smarthealthcare.entity.Rehabilitation;
import com.silverfox.smarthealthcare.exception.RehabilitationNotFoundException;
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

        Biometric biometric = modelMapper.map(biometricRequest, Biometric.class);

        biometric.setRehabilitation(rehabilitation);
        biometricRepository.save(biometric);
    }
}
