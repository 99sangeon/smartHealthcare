package com.silverfox.smarthealthcare.converter;


import com.silverfox.smarthealthcare.enums.Gender;

import org.springframework.core.convert.converter.Converter;

public class GenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {

        try {
            return Gender.valueOf(source);
        } catch (Exception e) {
            throw new IllegalStateException("유효한 성별을 선택해주세요.");
        }

    }

}
