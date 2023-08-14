package com.silverfox.smarthealthcare.controller;

import com.silverfox.smarthealthcare.dto.RehabilitationResponse;
import com.silverfox.smarthealthcare.entity.Patient;
import com.silverfox.smarthealthcare.service.FCMService;
import com.silverfox.smarthealthcare.service.PatientService;
import com.silverfox.smarthealthcare.service.RehabilitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fcm")
public class FCMController {

    private final FCMService fcmService;
    private final RehabilitationService rehabilitationService;

    @Value("${fcm.target-token}")
    private String targetToken;

    @RequestMapping("/call/{rehabilitationId}")
    public ResponseEntity call(@PathVariable("rehabilitationId") Long id) throws IOException {

        RehabilitationResponse rehabilitation = rehabilitationService.getRehabilitation(id);

        fcmService.sendMessageTo(
                targetToken,
                "관리자호출",
                rehabilitation.getPatient().getName() + "님이 호출했습니다.");

        return ResponseEntity.ok().build();
    }


    @RequestMapping("/emergency/{rehabilitationId}")
    public ResponseEntity emergency(@PathVariable("rehabilitationId") Long id) throws IOException {

        RehabilitationResponse rehabilitation = rehabilitationService.getRehabilitation(id);

        fcmService.sendMessageTo(
                targetToken,
                "안전사고",
                rehabilitation.getPatient().getName() + "님에게 안전사고가 발생했습니다.");

        return ResponseEntity.ok().build();

    }


}