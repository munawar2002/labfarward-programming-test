package com.labfarward.programmingtest.service;

import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.model.Category;
import com.labfarward.programmingtest.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category saveCategory(CategoryRequestDto categoryRequestDto){

        return categoryRepository.findByNameAndActiveIsTrueAndDeletedIsFalse(categoryRequestDto.getName())
                .orElseGet(() -> saveNewCategory(categoryRequestDto));
    }

    private Category saveNewCategory(CategoryRequestDto categoryRequestDto) {
        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .description(categoryRequestDto.getDescription())
                .active(true)
                .build();

        return categoryRepository.save(category);
    }

}
