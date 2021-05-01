package com.labfarward.programmingtest.facade;

import com.labfarward.programmingtest.constants.exception.ErrorType;
import com.labfarward.programmingtest.dto.spec.*;
import com.labfarward.programmingtest.exception.UserException;
import com.labfarward.programmingtest.mapper.CategoryMapper;
import com.labfarward.programmingtest.mapper.ItemMapper;
import com.labfarward.programmingtest.model.Category;
import com.labfarward.programmingtest.model.Item;
import com.labfarward.programmingtest.model.ItemAttributeValue;
import com.labfarward.programmingtest.repository.CategoryRepository;
import com.labfarward.programmingtest.repository.ItemAttributeValueRepository;
import com.labfarward.programmingtest.repository.ItemRepository;
import com.labfarward.programmingtest.service.AttributeDefinitionService;
import com.labfarward.programmingtest.service.CategoryService;
import com.labfarward.programmingtest.service.ItemService;
import com.labfarward.programmingtest.validator.ItemCategoryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemCategoryFacade {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final AttributeDefinitionService attributeDefinitionService;
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final ItemAttributeValueRepository itemAttributeValueRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public CategoryResponseDto saveCategory(CategoryRequestDto categoryRequestDto){

        ItemCategoryValidator.validateCategoryRequestDto(categoryRequestDto);

        Category category = categoryService.saveCategory(categoryRequestDto);

        List<AttributeDefinitionResponseDto> attributeDefinitions =
                attributeDefinitionService.saveAttributeDefinitions(categoryRequestDto,category);

        return CategoryMapper.mapToCategoryResponseDto(category,attributeDefinitions);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ItemResponseDto saveOrUpdateItem(Integer itemId, Integer categoryId, ItemRequestDto itemRequestDto){

        ItemCategoryValidator.validateItemRequestDto(categoryId,itemRequestDto);

        Category category =
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new UserException(ErrorType.CATEGORY_NOT_FOUND));

        Item item = itemService.saveItem(itemId,category,itemRequestDto);

        itemRequestDto.getAttributeValues().forEach(av -> itemService.saveOrUpdateItemAttributeValue(item,av));

        return ItemMapper.mapToItemResponseDto(item, itemRequestDto.getAttributeValues());
    }

    public List<ItemResponseDto> getItems(int categoryId){

        Category category =
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new UserException(ErrorType.CATEGORY_NOT_FOUND));

        List<Item> items = itemRepository.findByCategoryAndActiveIsTrueAndDeletedIsFalse(category);

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        items.forEach(item -> itemResponseDtos.add(mapToItemResponseDto(item)));

        return itemResponseDtos;
    }

    private ItemResponseDto mapToItemResponseDto(Item item) {
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        List<AttributeValueDto> attributeValueDtos = new ArrayList<>();

        itemAttributeValueRepository.findByItem(item).forEach(iav -> attributeValueDtos.add(new AttributeValueDto()
                            .attributeName(iav.getAttributeDefinition().getName())
                            .value(iav.getValue())));

        itemResponseDto.setName(item.getName());
        itemResponseDto.setDescription(item.getDescription());
        itemResponseDto.setActive(item.isActive());
        itemResponseDto.setDeleted(item.isDeleted());
        itemResponseDto.setCategoryName(item.getCategory().getName());
        itemResponseDto.setAttributeValues(attributeValueDtos);
        return itemResponseDto;
    }

}
