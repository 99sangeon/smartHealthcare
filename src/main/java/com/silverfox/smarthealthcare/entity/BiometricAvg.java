package com.silverfox.smarthealthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BiometricAvg {

    private Float bpmAvg;

    private Float temperatureAvg;

    private Float oxygenSaturationAvg;

}

