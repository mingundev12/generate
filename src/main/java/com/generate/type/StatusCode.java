package com.generate.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    NORMAL("N", "정상"),
    ERROR("E", "이상"),
    MAINTAIN("M", "점검");

    private final String code;
    private final String description;
}
