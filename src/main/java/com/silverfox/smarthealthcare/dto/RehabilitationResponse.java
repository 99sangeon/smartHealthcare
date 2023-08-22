package com.silverfox.smarthealthcare.dto;

import com.silverfox.smarthealthcare.enums.RehabilitationStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RehabilitationResponse {

    private Long id;

    private PatientResponse patient;

    private List<BiometricResponse> biometrics;

    private RehabilitationStatus rehabilitationStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int goalTime;

    private Integer actualTime;

    private Integer remainingTime;

    private Float travelRange;

    private Float slope;

    private Float speed;

    private Float consumedCalories;

    private BiometricAvgResponse biometricAvg;

}
