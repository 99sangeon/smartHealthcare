package com.silverfox.smarthealthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RehabilitationAvgResponse {

    private BiometricAvgResponse biometricAvg;
    private float actualTimeAvg;
    private float consumedCaloriesAvg;
    private float speedAvg;
    private float travelRangeAvg;
    private float slopeAvg;

    public void setBiometricAvg(BiometricAvgResponse biometricAvg) {
        this.biometricAvg = biometricAvg;
    }
}
