package com.labfarward.programmingtest.mapper;

import com.labfarward.programmingtest.dto.spec.AttributeDefinitionResponseDto;
import com.labfarward.programmingtest.dto.spec.CategoryResponseDto;
import com.labfarward.programmingtest.model.Category;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.List;

@UtilityClass
public class CategoryMapper {

    public CategoryResponseDto mapToCategoryResponseDto(Category category,
                                                        List<AttributeDefinitionResponseDto> attributeDefinitionResponseDtos){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        BeanUtils.copyProperties(category,categoryResponseDto);
        categoryResponseDto.setAttributeDefinitions(attributeDefinitionResponseDtos);
        return categoryResponseDto;
    }

}
