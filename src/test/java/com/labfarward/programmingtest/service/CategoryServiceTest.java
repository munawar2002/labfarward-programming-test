package com.labfarward.programmingtest.service;

import com.labfarward.programmingtest.dto.spec.CategoryRequestDto;
import com.labfarward.programmingtest.helper.SampleObjects;
import com.labfarward.programmingtest.model.Category;
import com.labfarward.programmingtest.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @DisplayName("Test save category")
    @Test
    void testSaveCategory() {

        CategoryRequestDto categoryRequestDto = SampleObjects.getCategoryRequestDto();

        when(categoryRepository.save(SampleObjects.getCategory()))
                .thenReturn(SampleObjects.getCategory());

        Category actual = categoryService.saveCategory(categoryRequestDto);

        assertEquals(SampleObjects.getCategory(), actual);
    }

}
