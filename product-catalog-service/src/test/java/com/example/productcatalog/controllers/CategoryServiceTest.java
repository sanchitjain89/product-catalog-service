package com.example.productcatalog.controllers;

import com.example.productcatalog.models.Category;
import com.example.productcatalog.repository.CategoryRepository;
import com.example.productcatalog.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(1L, "Electronics", "Devices and gadgets", true, null, null);
    }

    @Test
    void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));

        List<Category> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Electronics", result.get(0).getName());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoryById_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.getCategoryById(1L);

        assertTrue(result.isPresent());
        assertEquals("Electronics", result.get().getName());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Category> result = categoryService.getCategoryById(1L);

        assertFalse(result.isPresent());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateCategory() {
        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.createCategory(category);

        assertNotNull(result);
        assertEquals("Electronics", result.getName());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testUpdateCategory_Success() {
        // Arrange: Set up mock objects and expected behavior
        Category updatedCategory = new Category();
        updatedCategory.setName("Home Appliances");
        updatedCategory.setDescription("Appliances for daily use");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category)); // Mock findById
        when(categoryRepository.save(any(Category.class))).thenReturn(updatedCategory); // Mock save behavior

        // Act: Call the method under test
        Category result = categoryService.updateCategory(1L, updatedCategory);

        // Assert: Verify the results
        assertNotNull(result);
        assertEquals("Home Appliances", result.getName());
        assertEquals("Appliances for daily use", result.getDescription());
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }


    @Test
    void testUpdateCategory_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoryService.updateCategory(1L, category));
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteCategory() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }
}

