package com.generate.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    UNDEFINED("UN", "예측"),
    PENDING("PE", "실측"),
    NORMAL("N", "정상"),
    ERROR("E", "이상"),
    MAINTAIN("M", "점검"),
    NIGHT("NI", "야간");

    private final String code;
    private final String description;
}
