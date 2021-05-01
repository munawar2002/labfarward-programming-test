package com.labfarward.programmingtest.validator;

import com.labfarward.programmingtest.constants.exception.ErrorType;
import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.exception.UserException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class ItemCategoryValidator {

    public void validateCategoryRequestDto(CategoryRequestDto categoryRequestDto) {
        validateNotNull(categoryRequestDto.getName(), ErrorType.CATEGORY_NAME_IS_NULL);
        categoryRequestDto.getAttributeDefinitions().forEach(ad -> {
            validateNotNull(ad.getName(), ErrorType.ATTRIBUTE_DEFINITION_NAME_IS_NULL);
            validateNotNull(ad.getType(), ErrorType.ATTRIBUTE_DEFINITION_TYPE_IS_NULL);
        });
    }

    private static void validateNotNull(String name, ErrorType categoryNameIsNull) {
        if (StringUtils.isEmpty(name)) {
            throw new UserException(categoryNameIsNull);
        }
    }

}
