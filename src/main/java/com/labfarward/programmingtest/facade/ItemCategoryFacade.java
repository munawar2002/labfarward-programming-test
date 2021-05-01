package com.labfarward.programmingtest.facade;

import com.labfarward.programmingtest.dto.spec.AttributeDefinitionResponseDto;
import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.dto.spec.CategoryResponseDto;
import com.labfarward.programmingtest.mapper.CategoryMapper;
import com.labfarward.programmingtest.model.Category;
import com.labfarward.programmingtest.service.AttributeDefinitionService;
import com.labfarward.programmingtest.service.CategoryService;
import com.labfarward.programmingtest.validator.ItemCategoryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemCategoryFacade {

    private final CategoryService categoryService;
    private final AttributeDefinitionService attributeDefinitionService;

    public CategoryResponseDto saveCategory(CategoryRequestDto categoryRequestDto){

        ItemCategoryValidator.validateCategoryRequestDto(categoryRequestDto);

        Category category = categoryService.saveCategory(categoryRequestDto);

        List<AttributeDefinitionResponseDto> attributeDefinitions =
                attributeDefinitionService.saveAttributeDefinitions(categoryRequestDto,category);

        return CategoryMapper.mapToCategoryResponseDto(category,attributeDefinitions);
    }



}
