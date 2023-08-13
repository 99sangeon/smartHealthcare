package com.silverfox.smarthealthcare.dto;

import com.silverfox.smarthealthcare.enums.Gender;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    @Length(max = 30, message = "이름은 30글자 이내로 입력해주세요.")
    private String name;

    @Positive(message = "나이를 정확하게 입력해주세요.")
    @NotNull(message = "나이를 입력해주세요.")
    private Integer age;

    @NotNull(message = "생년월일을 선택해주세요.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    @NotNull(message = "성별을 선택해주세요.")
    private Gender gender;

    @Positive(message = "신장을 정확하게 입력해주세요.")
    @NotNull(message = "신장을 입력해주세요.")
    private Float height;

    @Positive(message = "몸무게를 정확하게 입력해주세요.")
    @NotNull(message = "몸무게를 입력해주세요.")
    private Float weight;

    @NotBlank(message = "휴대전화 번호를 입력해주세요.")
    @Length(max = 15, message = "휴대전화 번호의 최대 입력 길이는 15글자입니다.")
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "유효한 휴대전화번호 입력해주세요.")
    private String mobile;

}

