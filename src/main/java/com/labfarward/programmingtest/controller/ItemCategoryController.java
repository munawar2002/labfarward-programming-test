package com.labfarward.programmingtest.controller;

import com.labfarward.programmingtest.controller.spec.CategoriesApi;
import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.dto.spec.CategoryResponseDto;
import com.labfarward.programmingtest.dto.spec.ItemRequestDto;
import com.labfarward.programmingtest.dto.spec.ItemResponseDto;
import com.labfarward.programmingtest.facade.ItemCategoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ItemCategoryController implements CategoriesApi {

    private final ItemCategoryFacade itemCategoryFacade;

    @Override
    public ResponseEntity<CategoryResponseDto> saveCategory(@Valid CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = itemCategoryFacade.saveCategory(categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ItemResponseDto> saveItem(Integer categoryId, @Valid ItemRequestDto itemRequestDto) {
        ItemResponseDto itemResponseDto = itemCategoryFacade.saveItem(categoryId,itemRequestDto);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.CREATED);
    }
}
