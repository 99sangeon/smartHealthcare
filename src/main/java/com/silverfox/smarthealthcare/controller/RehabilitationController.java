package com.silverfox.smarthealthcare.controller;

import com.silverfox.smarthealthcare.dto.*;
import com.silverfox.smarthealthcare.entity.Rehabilitation;
import com.silverfox.smarthealthcare.service.BiometricService;
import com.silverfox.smarthealthcare.service.RehabilitationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/rehabilitations")
public class RehabilitationController {

    private final RehabilitationService rehabilitationService;
    private final BiometricService biometricService;

    @GetMapping
    public String rehabilitations(Model model) {

        List<RehabilitationSimpleResponse> rehabilitations = rehabilitationService.getRehabilitationList();
        model.addAttribute("rehabilitations", rehabilitations);

        return "/rehabilitation/list";
    }

    @GetMapping( "/{rehabilitationId}")
    public String rehabilitation(@PathVariable("rehabilitationId") Long id, Model model) {

        RehabilitationResponse rehabilitation = rehabilitationService.getRehabilitation(id);

        model.addAttribute("rehabilitation", rehabilitation);

        return "/rehabilitation/detail";
    }

    @ResponseBody
    @GetMapping("/{rehabilitationId}/calculate-averages/{compareCnt}")
    public ResponseEntity<RehabilitationAvgResponse> rehabilitationAvg(@PathVariable("rehabilitationId") Long id,
                                                                       @PathVariable("compareCnt") int compareCnt) {

        RehabilitationAvgResponse rehabilitationAvg = rehabilitationService.getRehabilitationAvg(id, compareCnt);
        return ResponseEntity.ok(rehabilitationAvg);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Map> rehabilitationSave(@RequestBody RehabilitationInitialRequest initialRequest) {

        Long id = rehabilitationService.saveRehabilitation(initialRequest);

        Map<String, Long> map = new HashMap<>();
        map.put("rehabilitationId", id);

        return ResponseEntity.ok(map);
    }

    @PostMapping("/{rehabilitationId}/start")
    @ResponseBody
    public ResponseEntity start(@PathVariable("rehabilitationId") Long id) {

        rehabilitationService.rehabilitationStart(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{rehabilitationId}/end")
    @ResponseBody
    public ResponseEntity end(@PathVariable("rehabilitationId") Long id, @RequestBody RehabilitationEndRequest endRequest) {

        rehabilitationService.rehabilitationEnd(id, endRequest);

        return ResponseEntity.ok().build();
    }




}
