package com.generate.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProvinceCode {
    DAEJEON("30", "대전광역시", "대전");

    private final String provinceCode; // 광역 코드
    private final String description; // 광역단체명
    private final String shortDesc; // 광역단체명 약칭

    public static ProvinceCode fromCode(String stateCode) {
        return Arrays.stream(ProvinceCode.values())
                .filter(province -> province.provinceCode.equals(stateCode))
                .findFirst()
                .orElse(null);
    }
}