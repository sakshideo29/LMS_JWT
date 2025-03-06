package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ProductDao;
import com.demo.model.Product;

/**
 * Implementation of ProductService interface.
 * Provides CRUD operations for products and utilizes logging to track the operations.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    // Remove field injection and use constructor injection
    private final ProductDao pdao;

    // Constructor injection for ProductDao
    @Autowired
    public ProductServiceImpl(ProductDao pdao) {
        this.pdao = pdao;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return List of all products.
     */
    @Override
    public List<Product> getAllProducts() {
        return pdao.findAll();
    }

    /**
     * Adds a new product to the database.
     *
     * @param p The product to be added.
     */
    @Override
    public void addNewProduct(Product p) {
        logger.info("Adding new product with name: {}", p.getPname());
        pdao.save(p);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param pid The ID of the product.
     * @return The product with the specified ID.
     */
    @Override
    public Product getById(int pid) {
        Optional<Product> op = pdao.findById(pid);
        if (op.isPresent()) {
            return op.get();
        }
        return null; // Consider throwing a ProductNotFoundException
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product The updated product information.
     */
    @Override
    public void updateById(Product product) {
        Optional<Product> op = pdao.findById(product.getPid());
        if (op.isPresent()) {
            Product p = op.get();
            p.setPname(product.getPname());
            p.setQty(product.getQty());
            p.setPrice(product.getPrice());
            pdao.save(p);
        }
    }

    /**
     * Deletes a product from the database.
     *
     * @param id The ID of the product to be deleted.
     */
    @Override
    public void deleteById(int id) {
        pdao.deleteById(id);
    }

    /**
     * Retrieves products by price range.
     *
     * @param lpr The lower price range.
     * @param hpr The higher price range.
     * @return List of products within the specified price range.
     */
    @Override
    public List<Product> getByPrice(int lpr, int hpr) {
        List<Product> plist = pdao.findProductsByPriceRange(lpr, hpr);
        logger.info("Found products by price range: {} - {}", lpr, hpr);
        plist.forEach(product -> logger.debug("Product: {}", product));
        return plist;
    }
}
