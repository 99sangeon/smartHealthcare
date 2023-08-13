package com.silverfox.smarthealthcare.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BiometricRequest {

    private float bpm;                    //맥박

    private float oxygenSaturation;         //산소포화도

    private float temperature;              //체온

    private Long rehabilitationId;

}
