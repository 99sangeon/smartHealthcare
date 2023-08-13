package com.silverfox.smarthealthcare.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RehabilitationNotFoundException extends RuntimeException{

    public RehabilitationNotFoundException(Long id) {
        super("재활 ID:" + id + " 해당 재활아이디를 찾을 수 없습니다.");
    }
}
