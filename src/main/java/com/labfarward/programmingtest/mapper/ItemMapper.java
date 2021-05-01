package com.labfarward.programmingtest.mapper;

import com.labfarward.programmingtest.dto.spec.AttributeValueDto;
import com.labfarward.programmingtest.dto.spec.CategoryResponseDto;
import com.labfarward.programmingtest.dto.spec.ItemResponseDto;
import com.labfarward.programmingtest.model.Item;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.List;

@UtilityClass
public class ItemMapper {

    public ItemResponseDto mapToItemResponseDto(Item item, List<AttributeValueDto> attributeValueDtos){
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        BeanUtils.copyProperties(item,itemResponseDto);
        itemResponseDto.setCategoryName(item.getCategory().getName());
        itemResponseDto.setAttributeValues(attributeValueDtos);
        return itemResponseDto;
    }

}
