package com.silverfox.smarthealthcare.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BiometricResponse {

    private int bpm;

    private int temperature;

    private float oxygenSaturation;

    private LocalDateTime createdDate;

}
