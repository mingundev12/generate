package com.generate.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DistrictCode {
    DAEJEON_JUNG("30140", ProvinceCode.DAEJEON, "중구",
            new BigDecimal("36.325781"), new BigDecimal("127.42133"));
    // TODO: 다른 행정구역도 추가

    private final String districtCode; // 지역 코드(기초단체 행정동코드 5자리)
    private final ProvinceCode state; // 광역 코드
    private final String description; // 지역명
    private final BigDecimal latitude; // 위도
    private final BigDecimal longitude; // 경도
    private final String timezone = "Asia/Tokyo"; // 기상조회용 시간대

    public String getRegionShortName() {
        return state.getShortDesc() + " " + description;
    }

    public String getRegionFullName() {
        return state.getDescription() + " " + description;
    }

    public static DistrictCode fromCode(String code) {
        return Arrays.stream(DistrictCode.values())
                .filter(district -> district.districtCode.equals(code))
                .findFirst()
                .orElse(null);
    }
}
