package com.labfarward.programmingtest.helper;

import com.labfarward.programmingtest.dto.spec.AttributeDefinitionRequestDto;
import com.labfarward.programmingtest.dto.spec.AttributeDefinitionResponseDto;
import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.dto.spec.CategoryResponseDto;
import com.labfarward.programmingtest.model.AttributeDefinition;
import com.labfarward.programmingtest.model.Category;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class SampleObjects {

    public AttributeDefinition getAttributeDefinitionRequest() {
        return AttributeDefinition.builder()
                .active(true)
                .deleted(false)
                .name("Attribute1")
                .description("Attribute1")
                .type("string")
                .category(getCategory())
                .build();
    }

    public AttributeDefinition getAttributeDefinitionResponse() {
        return AttributeDefinition.builder()
                .id(1)
                .active(true)
                .deleted(false)
                .name("Attribute1")
                .description("Attribute1")
                .type("string")
                .category(null)
                .createdBy("user")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    public List<AttributeDefinition> getAttributeDefinitionResponseList() {
        return Collections.singletonList(AttributeDefinition.builder()
                .id(1)
                .active(true)
                .deleted(false)
                .name("Attribute1")
                .description("Attribute1")
                .type("string")
                .category(null)
                .createdBy("user")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build());
    }

    public Category getCategory() {
        return Category.builder()
                .name("Category1")
                .description("Category1")
                .active(true)
                .deleted(false)
                .build();
    }

    public CategoryRequestDto getCategoryRequestDto() {
        return new CategoryRequestDto()
                .name("Category1")
                .description("Category1")
                .attributeDefinitions(getAttributeDefinitionRequestDto());
    }

    public CategoryResponseDto getCategoryResponseDto() {
        return new CategoryResponseDto()
                .name("Category1")
                .description("Category1")
                .attributeDefinitions(getAttributeDefinitionResponseDto())
                .active(true)
                .deleted(false);
    }

    public List<AttributeDefinitionRequestDto> getAttributeDefinitionRequestDto() {

        List<AttributeDefinitionRequestDto> attributeDefinitionRequestDtos = new ArrayList<>();

        attributeDefinitionRequestDtos.add(new AttributeDefinitionRequestDto()
                .name("Attribute1")
                .description("Attribute1")
                .type("string"));

        return attributeDefinitionRequestDtos;
    }

    public List<AttributeDefinitionResponseDto> getAttributeDefinitionResponseDto() {

        List<AttributeDefinitionResponseDto> attributeDefinitionResponseDtos = new ArrayList<>();

        attributeDefinitionResponseDtos.add(new AttributeDefinitionResponseDto()
                .name("Attribute1")
                .description("Attribute1")
                .type("string")
                .active(true)
                .deleted(false));

        return attributeDefinitionResponseDtos;
    }

}