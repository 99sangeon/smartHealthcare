package com.silverfox.smarthealthcare.exception;


public class RehabilitationAvgNotFoundException extends RuntimeException{

    public RehabilitationAvgNotFoundException() {
        super("이전 재활 기록이 존재하지 않습니다.");
    }

}
