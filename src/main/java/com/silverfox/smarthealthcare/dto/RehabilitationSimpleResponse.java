package com.silverfox.smarthealthcare.dto;

import com.silverfox.smarthealthcare.enums.RehabilitationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RehabilitationSimpleResponse {

    private Long id;

    private PatientResponse patientResponse;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private RehabilitationStatus rehabilitationStatus;

    public void setPatientResponse(PatientResponse patientResponse) {
        this.patientResponse = patientResponse;
    }
}
