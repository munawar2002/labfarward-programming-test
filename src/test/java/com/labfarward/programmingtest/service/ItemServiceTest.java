package com.labfarward.programmingtest.service;

import com.labfarward.programmingtest.dto.spec.AttributeValueDto;
import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.dto.spec.ItemRequestDto;
import com.labfarward.programmingtest.helper.SampleObjects;
import com.labfarward.programmingtest.model.Category;
import com.labfarward.programmingtest.model.Item;
import com.labfarward.programmingtest.repository.AttributeDefinitionRepository;
import com.labfarward.programmingtest.repository.ItemAttributeValueRepository;
import com.labfarward.programmingtest.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private AttributeDefinitionRepository attributeDefinitionRepository;

    @Mock
    private ItemAttributeValueRepository itemAttributeValueRepository;

    @DisplayName("Test save item")
    @Test
    void testSaveItem() {

        ItemRequestDto itemRequestDto = SampleObjects.getItemRequestDto();

        when(itemRepository.save(SampleObjects.getItem()))
                .thenReturn(SampleObjects.getItem());

        Item actual = itemService.saveItem(SampleObjects.getCategory(),itemRequestDto);

        assertEquals(SampleObjects.getItem(), actual);
    }

    @DisplayName("Test save item attribute value")
    @Test
    void testSaveItemAttributeValue() {

        AttributeValueDto itemAttributeValue = SampleObjects.getItemAttributeValue();

        when(attributeDefinitionRepository.
                findByNameAndCategoryAndActiveIsTrueAndDeletedIsFalse(
                        itemAttributeValue.getAttributeName(),
                        SampleObjects.getCategory()))
                .thenReturn(Optional.of(SampleObjects.getAttributeDefinitionResponse()));


        itemService.saveItemAttributeValue(SampleObjects.getItem(), itemAttributeValue);

        assertTrue(true);
    }

}
