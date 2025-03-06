package com.demo;

import com.demo.dao.ProductDao;
import com.demo.model.Product;
import com.demo.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mock objects
    }

    @Test
    void testGetAllProducts() {
        // Prepare mock data
        Product product = new Product(1, "Product1", 10, 100.0);
        when(productDao.findAll()).thenReturn(List.of(product));  // Mock DAO response

        // Call service method
        List<Product> products = productService.getAllProducts();

        // Verify results
        assertEquals(1, products.size());
        assertEquals("Product1", products.get(0).getPname());
    }

    @Test
    void testAddNewProduct() {
        // Prepare test data
        Product product = new Product(1, "Product1", 10, 100.0);

        // Call service method
        productService.addNewProduct(product);

        // Verify the DAO method call
        verify(productDao, times(1)).save(product);  // Ensure save() was called once
    }

    @Test
    void testUpdateProduct() {
        // Prepare test data
        Product product = new Product(1, "UpdatedProduct", 15, 150.0);
        when(productDao.findById(1)).thenReturn(Optional.of(new Product(1, "OldProduct", 10, 100.0)));  // Mock findById response

        // Call service method
        productService.updateById(product);

        // Verify the DAO method call
        verify(productDao, times(1)).save(product);  // Ensure save() is called to persist updates
    }

    @Test
    void testDeleteProduct() {
        // Call service method
        productService.deleteById(1);

        // Verify the DAO method call
        verify(productDao, times(1)).deleteById(1);  // Ensure deleteById() is called once
    }

    @Test
    void testGetByPrice() {
        // Prepare mock data
        Product product = new Product(1, "Product1", 10, 100.0);
        when(productDao.findProductsByPriceRange(50, 150)).thenReturn(List.of(product));  // Mock DAO response

        // Call service method
        List<Product> products = productService.getByPrice(50, 150);

        // Verify results
        assertEquals(1, products.size());
        assertEquals("Product1", products.get(0).getPname());
    }
}
