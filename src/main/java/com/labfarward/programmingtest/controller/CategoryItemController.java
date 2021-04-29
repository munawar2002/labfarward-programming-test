package com.labfarward.programmingtest.controller;

import com.labfarward.programmingtest.controller.spec.CategoriesApi;
import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.dto.spec.CategoryResponseDto;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public class CategoryItemController implements CategoriesApi {

    @Override
    public ResponseEntity<CategoryResponseDto> getAllCategories() {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResponseDto> getCategory(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResponseDto> saveCategory(@Valid CategoryRequestDto categoryRequestDto) {

        return null;
    }
}
