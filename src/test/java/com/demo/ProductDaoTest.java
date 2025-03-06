package com.demo;

import com.demo.dao.ProductDao;
import com.demo.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    private Product product1;
    private Product product2;

    /**
     * Set up test data before each test.
     */
    @BeforeEach
    void setUp() {
        // Initialize test data
        product1 = new Product(1, "Product 1", 10, 50.0);
        product2 = new Product(2, "Product 2", 20, 150.0);

        // Save products to the database
        productDao.save(product1);
        productDao.save(product2);
    }

    /**
     * Test the custom query to find products within a price range.
     */
    @Test
    void testFindByPriceRange() {
        // Given a price range between 40 and 100
        int lowPrice = 40;
        int highPrice = 100;

        // When findByPrice is called
        List<Product> products = productDao. findProductsByPriceRange(lowPrice, highPrice);

        // Then verify that we only get products within that price range
        assertNotNull(products, "Product list should not be null");
        assertTrue(products.size() > 0, "Product list should contain products");
        for (Product product : products) {
            assertTrue(product.getPrice() >= lowPrice && product.getPrice() <= highPrice,
                    "Product price should be within the given range");
        }
    }

    /**
     * Test saving a product and retrieving it by its ID.
     */
    @Test
    void testSaveAndFindById() {
        // Save a new product
        Product product = new Product(3, "Product 3", 30, 250.0);
        productDao.save(product);

        // Find the product by ID
        Product foundProduct = productDao.findById(3).orElse(null);

        // Assert that the product is found and matches the saved one
        assertNotNull(foundProduct, "Product should be found by ID");
        assertEquals(3, foundProduct.getPid(), "Product ID should match");
        assertEquals("Product 3", foundProduct.getPname(), "Product name should match");
        assertEquals(250.0, foundProduct.getPrice(), "Product price should match");
    }

    /**
     * Test deleting a product by its ID.
     */
    @Test
    void testDeleteById() {
        // Delete a product (using the one saved in setUp())
        productDao.deleteById(1);

        // Assert that the product is deleted
        Product deletedProduct = productDao.findById(1).orElse(null);
        assertNull(deletedProduct, "Product should be null after deletion");
    }

    /**
     * Clean up data after each test (optional, depends on your test strategy).
     */
    // @AfterEach
    // void cleanUp() {
    //     productDao.deleteAll();
    // }
}
