package com.labfarward.programmingtest.service;

import com.labfarward.programmingtest.constants.exception.ErrorType;
import com.labfarward.programmingtest.dto.spec.AttributeValueDto;
import com.labfarward.programmingtest.dto.spec.ItemRequestDto;
import com.labfarward.programmingtest.exception.UserException;
import com.labfarward.programmingtest.model.AttributeDefinition;
import com.labfarward.programmingtest.model.Category;
import com.labfarward.programmingtest.model.Item;
import com.labfarward.programmingtest.model.ItemAttributeValue;
import com.labfarward.programmingtest.repository.AttributeDefinitionRepository;
import com.labfarward.programmingtest.repository.ItemAttributeValueRepository;
import com.labfarward.programmingtest.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemAttributeValueRepository itemAttributeValueRepository;
    private final AttributeDefinitionRepository attributeDefinitionRepository;

    public Item saveItem(Integer itemId, Category category, ItemRequestDto itemRequestDto) {
        return itemRepository.findById(itemId == null ? 0 : itemId)
                .orElseGet(() -> saveNewItem(category, itemRequestDto));
    }

    private Item saveNewItem(Category category, ItemRequestDto itemRequestDto) {
        Item item = Item.builder()
                .name(itemRequestDto.getName())
                .description(itemRequestDto.getDescription())
                .active(true)
                .deleted(false)
                .category(category)
                .build();

        return itemRepository.save(item);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdateItemAttributeValue(Item item, AttributeValueDto attributeValueDto) {

        AttributeDefinition attributeDefinition =
                attributeDefinitionRepository
                        .findByNameAndCategoryAndActiveIsTrueAndDeletedIsFalse(
                                attributeValueDto.getAttributeName()
                                , item.getCategory())
                        .orElseThrow(() -> new UserException(ErrorType.ATTRIBUTE_DEFINITION_NOT_FOUND));

        Optional<ItemAttributeValue> optionalItemAttributeValue =
                itemAttributeValueRepository.findByItemAndAttributeDefinition(item,attributeDefinition);

        ItemAttributeValue itemAttributeValue;
        if(optionalItemAttributeValue.isPresent()){
            itemAttributeValue = optionalItemAttributeValue.get();
            itemAttributeValue.setValue(attributeValueDto.getValue());
        } else {
            itemAttributeValue = ItemAttributeValue.builder()
                    .item(item)
                    .attributeDefinition(attributeDefinition)
                    .value(attributeValueDto.getValue())
                    .build();
        }

        itemAttributeValueRepository.save(itemAttributeValue);
    }
}
