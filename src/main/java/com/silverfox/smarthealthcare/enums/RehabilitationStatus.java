package com.silverfox.smarthealthcare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RehabilitationStatus {

    BEFORE("재활 시작 전"),
    ING("재활 중"),
    AFTER("재활 종료");

    private final String value;

}
