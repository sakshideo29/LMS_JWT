package com.demo;

import com.demo.controller.ProductController;
import com.demo.model.Product;
import com.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for ProductController
 */
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;  // Inject MockMvc to send HTTP requests

    @MockBean
    private ProductService productService;  // Mock ProductService

    /**
     * Test for the GET /product/getproducts endpoint.
     * It verifies that the controller returns the correct view and model attributes.
     */
    @Test
    void testGetProducts() throws Exception {
        // Prepare mock data
        Product product1 = new Product(1, "Product1", 10, 100.0);
        Product product2 = new Product(2, "Product2", 5, 50.0);
        List<Product> productList = Arrays.asList(product1, product2);

        // Mock the service call to return the product list
        when(productService.getAllProducts()).thenReturn(productList);

        // Perform GET request to /product/getproducts
        mockMvc.perform(get("/product/getproducts"))
                .andExpect(status().isOk())  // Expecting 200 OK status
                .andExpect(view().name("displayproduct"))  // Ensure the correct view name
                .andExpect(model().attribute("plist", productList));  // Ensure the model contains the correct products

        // Verify that the service method was called once
        verify(productService, times(1)).getAllProducts();
    }

    /**
     * Test for the POST /product/insertProduct endpoint.
     * It verifies that after adding a new product, the user is redirected to /product/getproducts.
     */
    @Test
    void testInsertProduct() throws Exception {
        // Prepare data for the new product
        Product product = new Product(1, "Product1", 10, 100.0);

        // Mock the service call to save the new product
        doNothing().when(productService).addNewProduct(any(Product.class));

        // Perform POST request to /product/insertProduct with parameters
        mockMvc.perform(post("/product/insertProduct")
                        .param("pid", "1")
                        .param("pname", "Product1")
                        .param("qty", "10")
                        .param("price", "100.0"))
                .andExpect(status().is3xxRedirection())  // Expecting a redirection after a successful insert
                .andExpect(redirectedUrl("/product/getproducts"));  // Ensure it redirects to the getproducts endpoint

        // Verify that the service method was called once
        verify(productService, times(1)).addNewProduct(any(Product.class));
    }

    // You can add more tests for other operations like update, delete, etc.
}
