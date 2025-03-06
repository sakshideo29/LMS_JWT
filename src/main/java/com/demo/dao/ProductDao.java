package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.Product;

/**
 * Repository interface for performing CRUD operations on the Product entity.
 * Extends JpaRepository to leverage built-in methods for common operations.
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    /**
     * Retrieves a list of products whose price is between the specified lower and upper price range.
     * 
     * @param lpr The lower price range.
     * @param hpr The higher price range.
     * @return List of products within the specified price range.
     */
    @Query(value = "SELECT * FROM producttab11 WHERE price BETWEEN :lpr AND :hpr", nativeQuery = true)
    List<Product> findProductsByPriceRange(int lpr, int hpr);



}
