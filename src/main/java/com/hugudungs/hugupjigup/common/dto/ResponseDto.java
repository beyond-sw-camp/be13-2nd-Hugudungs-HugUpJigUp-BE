package com.hugudungs.hugupjigup.common.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResponseDto<T> {
    private final int statusCode;
    private final String message;
    private final boolean result;
    private final T data;
}
