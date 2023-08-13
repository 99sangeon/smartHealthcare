package com.silverfox.smarthealthcare.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(Long id) {
        super("환자 ID:" + id + " 해당 환자를 찾을 수 없습니다.");
    }
}
