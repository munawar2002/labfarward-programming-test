package com.labfarward.programmingtest.mapper;

import com.labfarward.programmingtest.dto.spec.AttributeDefinitionResponseDto;
import com.labfarward.programmingtest.helper.SampleObjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AttributeDefinitionMapperTest {

    @DisplayName("Test mapper attribute definition response dtos")
    @Test
    void testMapToAttributeDefinitionResponseDto() {
        List<AttributeDefinitionResponseDto> actual =
                AttributeDefinitionMapper.mapToAttributeDefinitionResponseDto(SampleObjects.getAttributeDefinitionResponseList());

        assertEquals(SampleObjects.getAttributeDefinitionResponseDto(), actual);
    }

}
