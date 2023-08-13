package com.silverfox.smarthealthcare.dto;

import com.silverfox.smarthealthcare.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PatientResponse {

    private Long id;

    private String name;

    private int age;

    private LocalDate birthday;

    private Gender gender;

    private float height;

    private float weight;

    private String mobile;
}
