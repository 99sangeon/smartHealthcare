package com.silverfox.smarthealthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BiometricAvgResponse {
    private Float bpmAvg;

    private Float temperatureAvg;

    private Float oxygenSaturationAvg;

}
