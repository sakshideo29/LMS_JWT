package com.demo.service;

import java.util.List;

import com.demo.model.Product;

/**
 * Service interface for managing products.
 * Provides methods for CRUD operations on products.
 */
public interface ProductService {

    /**
     * Retrieves all products from the database.
     * 
     * @return List of all products.
     */
    List<Product> getAllProducts();

    /**
     * Adds a new product to the database.
     * 
     * @param p The product to be added.
     */
    void addNewProduct(Product p);

    /**
     * Retrieves a product by its ID.
     * 
     * @param pid The ID of the product.
     * @return The product with the specified ID.
     */
    Product getById(int pid);

    /**
     * Updates an existing product in the database.
     * 
     * @param product The product with updated information.
     */
    void updateById(Product product);

    /**
     * Deletes a product from the database by its ID.
     * 
     * @param id The ID of the product to be deleted.
     */
    void deleteById(int id);

    /**
     * Retrieves a list of products within a specific price range.
     * 
     * @param lpr The lower price range.
     * @param hpr The upper price range.
     * @return List of products within the specified price range.
     */
    List<Product> getByPrice(int lpr, int hpr);
}
