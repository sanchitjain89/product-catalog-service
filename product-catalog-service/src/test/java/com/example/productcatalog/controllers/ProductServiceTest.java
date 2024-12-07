package com.example.productcatalog.controllers;

import com.example.productcatalog.models.Category;
import com.example.productcatalog.models.Product;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        Category category = new Category(1L, "Electronics", "Devices and gadgets", true, null, null);
        product = new Product(1L, "Smartphone", "High-end phone", 699.99, 50, category, true, null);
    }

    @Test
    void testGetProductsByCategory() {
        Mockito.when(productRepository.findByCategoryCategoryIdAndIsActiveTrueAndInventoryGreaterThan(1L, 0))
                .thenReturn(Arrays.asList(product));

        List<Product> result = productService.getProductsByCategory(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Smartphone", result.get(0).getName());
        verify(productRepository, times(1)).findByCategoryCategoryIdAndIsActiveTrueAndInventoryGreaterThan(1L, 0);
    }

    @Test
    void testCreateProduct() {
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertNotNull(result);
        assertEquals("Smartphone", result.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct_Success() {
        Product updatedProduct = new Product(null, null, null, 799.99, 40, null, null, null);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.updateProduct(1L, updatedProduct);

        assertNotNull(result);
        assertEquals(799.99, result.getPrice());
        assertEquals(40, result.getInventory());
        Mockito.verify(productRepository, times(1)).findById(1L);
        Mockito.verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct_NotFound() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.updateProduct(1L, product));
        Mockito.verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteProduct() {
        Mockito.doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        Mockito.verify(productRepository, times(1)).deleteById(1L);
    }
}
