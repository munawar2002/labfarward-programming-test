package com.labfarward.programmingtest.mapper;

import com.labfarward.programmingtest.dto.spec.CategoryResponseDto;
import com.labfarward.programmingtest.dto.spec.ItemResponseDto;
import com.labfarward.programmingtest.helper.SampleObjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemMapperTest {

    @DisplayName("Test map to Item response dto ")
    @Test
    void testMapToItemResponseDto() {
        ItemResponseDto actual =
                ItemMapper.mapToItemResponseDto(SampleObjects.getItem(),SampleObjects.getAttributeValueDtos());

        assertEquals(SampleObjects.getItemResponseDto(), actual);
    }

}
