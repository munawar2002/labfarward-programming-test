package com.labfarward.programmingtest.exception;

import com.labfarward.programmingtest.constants.exception.ErrorType;
import lombok.Getter;

@Getter
public class UserException extends ItemCategoryException {

    ErrorType errorType;

    public UserException(ErrorType errorType) {
        super(errorType.getErrorTitle());
        this.errorType = errorType;
    }
}
