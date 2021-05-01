package com.labfarward.programmingtest.constants.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ErrorType {

    //SYSTEM errors
    UNKNOWN_ERROR("UP-1000", HttpStatus.INTERNAL_SERVER_ERROR,
            "Unrecognized Exception Occurred", ErrorCategory.SYSTEM),

    //VALIDATION errors
    CATEGORY_NAME_IS_NULL("UP-2000", HttpStatus.BAD_REQUEST,
            "Category name can't be null", ErrorCategory.VALIDATION),
    ATTRIBUTE_DEFINITION_NAME_IS_NULL("UP-2001", HttpStatus.BAD_REQUEST,
            "Attribute Definition name can't be null", ErrorCategory.VALIDATION),
    ATTRIBUTE_DEFINITION_TYPE_IS_NULL("UP-2002", HttpStatus.BAD_REQUEST,
            "Attribute Definition type can't be null", ErrorCategory.VALIDATION),
    CATEGORY_NOT_FOUND("UP-2003", HttpStatus.NOT_FOUND,
            "Category not found with provided categoryId as path param", ErrorCategory.VALIDATION),
    ATTRIBUTE_DEFINITION_NOT_FOUND("UP-2004", HttpStatus.NOT_FOUND,
            "AttributeDefinition not found with provided name", ErrorCategory.VALIDATION),
    ITEM_NAME_IS_NULL("UP-2005", HttpStatus.BAD_REQUEST,
            "Item name can't be null", ErrorCategory.VALIDATION),
    ATTRIBUTE_VALUE_IS_NULL("UP-2006", HttpStatus.BAD_REQUEST,
            "Attribute Value can't be null", ErrorCategory.VALIDATION),
    CATEGORY_ID_IS_NULL("UP-2007", HttpStatus.BAD_REQUEST,
            "Category id is null or less than zero", ErrorCategory.VALIDATION);


    private final String code;
    private final HttpStatus status;
    private final String errorTitle;
    private final ErrorCategory category;

    public static ErrorType parseErrorCode(String code) {
        if (code == null || "".equals(code)) {
            return null;
        }
        return Arrays.stream(ErrorType.values())
                .filter(er -> er.getCode().equals(code))
                .findFirst()
                .orElse(UNKNOWN_ERROR);
    }
}
