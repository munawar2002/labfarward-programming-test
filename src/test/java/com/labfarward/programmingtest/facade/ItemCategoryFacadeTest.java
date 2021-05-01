package com.labfarward.programmingtest.facade;

import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.dto.spec.CategoryResponseDto;
import com.labfarward.programmingtest.dto.spec.ItemRequestDto;
import com.labfarward.programmingtest.dto.spec.ItemResponseDto;
import com.labfarward.programmingtest.helper.SampleObjects;
import com.labfarward.programmingtest.repository.CategoryRepository;
import com.labfarward.programmingtest.service.AttributeDefinitionService;
import com.labfarward.programmingtest.service.CategoryService;
import com.labfarward.programmingtest.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemCategoryFacadeTest {

    @InjectMocks
    private ItemCategoryFacade itemCategoryFacade;

    @Mock
    private CategoryService categoryService;

    @Mock
    private AttributeDefinitionService attributeDefinitionService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ItemService itemService;

    private static final int CATEGORY_ID = 1;

    @DisplayName("Test save category")
    @Test
    void testSaveCategory() {

        CategoryRequestDto categoryRequestDto = SampleObjects.getCategoryRequestDto();

        when(categoryService.saveCategory(SampleObjects.getCategoryRequestDto()))
                .thenReturn(SampleObjects.getCategory());

        when(attributeDefinitionService.saveAttributeDefinitions(categoryRequestDto,SampleObjects.getCategory()))
                .thenReturn(SampleObjects.getAttributeDefinitionResponseDto());

        CategoryResponseDto actual = itemCategoryFacade.saveCategory(categoryRequestDto);

        assertEquals(SampleObjects.getCategoryResponseDto(), actual);
    }

    @DisplayName("Test save item")
    @Test
    void testSaveItem() {

        ItemRequestDto itemRequestDto = SampleObjects.getItemRequestDto();

        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.of(SampleObjects.getCategory())) ;

        when(itemService.saveItem(null,SampleObjects.getCategory(), itemRequestDto))
                .thenReturn(SampleObjects.getItem());

        ItemResponseDto actual = itemCategoryFacade.saveOrUpdateItem(null,CATEGORY_ID,itemRequestDto);

        assertEquals(SampleObjects.getItemResponseDto(), actual);
    }

    @DisplayName("Test update item")
    @Test
    void testUpdateItem() {

        ItemRequestDto itemRequestDto = SampleObjects.getItemRequestDto();

        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.of(SampleObjects.getCategory())) ;

        int itemId = 1;

        when(itemService.saveItem(itemId,SampleObjects.getCategory(), itemRequestDto))
                .thenReturn(SampleObjects.getItem());

        ItemResponseDto actual = itemCategoryFacade.saveOrUpdateItem(itemId,CATEGORY_ID,itemRequestDto);

        assertEquals(SampleObjects.getItemResponseDto(), actual);
    }

    @DisplayName("Test to get all items of category")
    @Test
    void testGetItemsByCategory() {

        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.of(SampleObjects.getCategory())) ;

        assertThrows(NullPointerException.class,() -> itemCategoryFacade.getItems(CATEGORY_ID));
    }
}
