package com.labfarward.programmingtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labfarward.programmingtest.constants.exception.ErrorType;
import lombok.Getter;

@Getter
public class ErrorResponseDto {

    @JsonProperty("errorCode")
    String errorCode;
    @JsonProperty("errorMessage")
    String errorMessage;

    public ErrorResponseDto(ErrorType errorType) {
        this.errorCode = errorType.getCode();
        this.errorMessage = errorType.getErrorTitle();
    }
}
