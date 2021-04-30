package com.labfarward.programmingtest.mapper;

import com.labfarward.programmingtest.dto.spec.AttributeDefinitionResponseDto;
import com.labfarward.programmingtest.model.AttributeDefinition;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AttributeDefinitionMapper {

    public List<AttributeDefinitionResponseDto> mapToAttributeDefinitionResponseDto(
            List<AttributeDefinition> attributeDefinitions) {

        List<AttributeDefinitionResponseDto> attributeDefinitionResponseDtos = new ArrayList<>();
        attributeDefinitions.forEach(ad -> {
            AttributeDefinitionResponseDto attributeDefinitionResponseDto = new AttributeDefinitionResponseDto();
            BeanUtils.copyProperties(ad,attributeDefinitionResponseDto);
            attributeDefinitionResponseDtos.add(attributeDefinitionResponseDto);
        });

        return attributeDefinitionResponseDtos;
    }

}
