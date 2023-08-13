package com.silverfox.smarthealthcare.controller;

import com.silverfox.smarthealthcare.dto.PatientRequest;
import com.silverfox.smarthealthcare.dto.PatientResponse;
import com.silverfox.smarthealthcare.enums.Gender;
import com.silverfox.smarthealthcare.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/api/patients")
    @ResponseBody
    public ResponseEntity<List<PatientResponse>> patients() {

        List<PatientResponse> patientList = patientService.getPatientList();

        return ResponseEntity.ok(patientList);

    }

    @GetMapping("/patients/registry")
    public String registryForm(Model model) {

        model.addAttribute("patient", new PatientRequest());
        return "/patient/registry";

    }

    @PostMapping("/patients/registry")
    public String registry(@Validated @ModelAttribute("patient") PatientRequest patientRequest,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("patient", patientRequest);
            return "/patient/registry";
        }

        patientService.savePatient(patientRequest);

        return "redirect:/";

    }

    @ModelAttribute("genders")
    public Gender[] itemTypes() {
        return Gender.values();
    }
}
