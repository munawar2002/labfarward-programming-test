package com.labfarward.programmingtest.validator;

import com.labfarward.programmingtest.constants.exception.ErrorType;
import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.dto.spec.ItemRequestDto;
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

    private static void validateNotNull(String name, ErrorType errorType) {
        if (StringUtils.isEmpty(name)) {
            throw new UserException(errorType);
        }
    }

    public void validateItemRequestDto(Integer categoryId,ItemRequestDto itemRequestDto) {
        validateId(categoryId,ErrorType.CATEGORY_ID_IS_NULL);
        validateNotNull(itemRequestDto.getName(), ErrorType.ITEM_NAME_IS_NULL);
        itemRequestDto.getAttributeValues().forEach(ad -> {
            validateNotNull(ad.getAttributeName(), ErrorType.ATTRIBUTE_DEFINITION_NAME_IS_NULL);
            validateNotNull(ad.getValue(), ErrorType.ATTRIBUTE_VALUE_IS_NULL);
        });
    }

    private static void validateId(Integer id, ErrorType errorType) {
        if(id == null || id <1){
            throw new UserException(errorType);
        }
    }

}
