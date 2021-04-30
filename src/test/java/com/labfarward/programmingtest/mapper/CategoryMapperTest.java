package com.labfarward.programmingtest.mapper;

import com.labfarward.programmingtest.dto.spec.AttributeDefinitionResponseDto;
import com.labfarward.programmingtest.dto.spec.CategoryResponseDto;
import com.labfarward.programmingtest.helper.SampleObjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CategoryMapperTest {

    @DisplayName("Test mapper category response dtos")
    @Test
    void testMapToCategoryResponseDto() {
        CategoryResponseDto actual =
                CategoryMapper.mapToCategoryResponseDto(SampleObjects.getCategory(),
                        SampleObjects.getAttributeDefinitionResponseDto());

        assertEquals(SampleObjects.getCategoryResponseDto(), actual);
    }

}
