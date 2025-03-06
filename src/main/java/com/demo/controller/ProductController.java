package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.Product;
import com.demo.service.ProductService;

/**
 * Controller class responsible for handling requests related to products.
 * This includes displaying products, adding, updating, deleting, and filtering by price.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService pservice;

    // Constructor injection (instead of field injection)
    @Autowired
    public ProductController(ProductService pservice) {
        this.pservice = pservice;
    }

    // Define string constants to avoid duplicated literals
    private static final String DISPLAY_PRODUCT_VIEW = "displayproduct";
    private static final String ADD_PRODUCT_VIEW = "addproduct";
    private static final String EDIT_PRODUCT_VIEW = "editProduct";
    private static final String REDIRECT_PRODUCTS = "redirect:/product/getproducts";

    /**
     * Handles the request to get all products and display them.
     *
     * @return ModelAndView containing the list of products.
     */
    @GetMapping("/getproducts")
    public ModelAndView getProducts() {
        List<Product> plist = pservice.getAllProducts();
        return new ModelAndView(DISPLAY_PRODUCT_VIEW, "plist", plist);
    }

    /**
     * Displays the form to add a new product.
     *
     * @return The view name for adding a product.
     */
    @GetMapping("/addproduct")
    public String displayAddForm() {
        return ADD_PRODUCT_VIEW;
    }

    /**
     * Handles the request to insert a new product.
     *
     * @param pid    The product ID.
     * @param pname  The product name.
     * @param qty    The product quantity.
     * @param price  The product price.
     * @return ModelAndView to redirect to the product list page.
     */
    @PostMapping("/insertProduct")
    public ModelAndView insertProduct(@RequestParam int pid,
                                      @RequestParam String pname,
                                      @RequestParam int qty,
                                      @RequestParam double price) {
        Product p = new Product(pid, pname, qty, price);
        pservice.addNewProduct(p); // Use the correct method name from ProductService interface
        return new ModelAndView(REDIRECT_PRODUCTS);
    }

    /**
     * Displays the form to edit a product.
     *
     * @param pid The ID of the product to be edited.
     * @return ModelAndView containing the product to be edited.
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int pid) {
        Product p = pservice.getById(pid);
        return new ModelAndView(EDIT_PRODUCT_VIEW, "p", p);
    }

    /**
     * Handles the request to update an existing product.
     *
     * @param pid    The product ID.
     * @param pname  The updated product name.
     * @param qty    The updated product quantity.
     * @param price  The updated product price.
     * @return ModelAndView to redirect to the product list page.
     */
    @PostMapping("/updateProduct")
    public ModelAndView updateProduct(@RequestParam int pid,
                                      @RequestParam String pname,
                                      @RequestParam int qty,
                                      @RequestParam double price) {
        pservice.updateById(new Product(pid, pname, qty, price));
        return new ModelAndView(REDIRECT_PRODUCTS);
    }

    /**
     * Handles the request to delete a product.
     *
     * @param id The ID of the product to be deleted.
     * @return ModelAndView to redirect to the product list page.
     */
    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable int id) {
        pservice.deleteById(id);
        return new ModelAndView(REDIRECT_PRODUCTS);
    }

    /**
     * Retrieves products within a specific price range.
     *
     * @param lpr The lower price range.
     * @param hpr The higher price range.
     * @return ModelAndView containing the filtered list of products.
     */
    @GetMapping("/products/price/{lpr}/{hpr}")
    public ModelAndView getProductByPrice(@PathVariable int lpr, @PathVariable int hpr) {
        List<Product> plist = pservice.getByPrice(lpr, hpr);
        return new ModelAndView(DISPLAY_PRODUCT_VIEW, "plist", plist);
    }
}
