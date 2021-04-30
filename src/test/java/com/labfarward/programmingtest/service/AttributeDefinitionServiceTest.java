package com.labfarward.programmingtest.service;

import com.labfarward.programmingtest.dto.spec.AttributeDefinitionResponseDto;
import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.helper.SampleObjects;
import com.labfarward.programmingtest.model.Category;
import com.labfarward.programmingtest.repository.AttributeDefinitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class AttributeDefinitionServiceTest {

    @InjectMocks
    private AttributeDefinitionService attributeDefinitionService;

    @Mock
    private AttributeDefinitionRepository attributeDefinitionRepository;

    @DisplayName("Test save attribute definition")
    @Test
    void testSaveAttributeDefinition() {

        Category category = SampleObjects.getCategory();
        CategoryRequestDto categoryRequestDto = SampleObjects.getCategoryRequestDto();


        when(attributeDefinitionRepository.save(SampleObjects.getAttributeDefinitionRequest()))
                .thenReturn(SampleObjects.getAttributeDefinitionResponse());

        List<AttributeDefinitionResponseDto> actual =
                attributeDefinitionService.saveAttributeDefinitions(categoryRequestDto,category);

        assertEquals(SampleObjects.getAttributeDefinitionResponseDto(), actual);
    }

}
