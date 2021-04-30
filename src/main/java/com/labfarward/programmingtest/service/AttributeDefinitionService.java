package com.labfarward.programmingtest.service;

import com.labfarward.programmingtest.dto.spec.AttributeDefinitionResponseDto;
import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.mapper.AttributeDefinitionMapper;
import com.labfarward.programmingtest.model.AttributeDefinition;
import com.labfarward.programmingtest.model.Category;
import com.labfarward.programmingtest.repository.AttributeDefinitionRepository;
import com.labfarward.programmingtest.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttributeDefinitionService {

    private final AttributeDefinitionRepository attributeDefinitionRepository;

    public List<AttributeDefinitionResponseDto> saveAttributeDefinitions(CategoryRequestDto categoryRequestDto,
                                                                         Category category){
        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();

        categoryRequestDto.getAttributeDefinitions().forEach(ad -> {
            AttributeDefinition attributeDefinition = AttributeDefinition.builder()
                    .name(ad.getName())
                    .description(ad.getDescription())
                    .active(true)
                    .type(ad.getType())
                    .category(category)
                    .build();

            attributeDefinitions.add(attributeDefinitionRepository.save(attributeDefinition));
        });

        return AttributeDefinitionMapper.mapToAttributeDefinitionResponseDto(attributeDefinitions);
    }

}
