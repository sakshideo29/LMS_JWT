package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity class representing a product in the system.
 * Maps to the `producttab11` table in the database.
 */
@Entity
@Table(name = "producttab11")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate PID if it's a database-generated ID
    private int pid;

    private String pname;
    private int qty;
    private double price;

    /**
     * Default constructor.
     */
    public Product() {
        super();
    }

    /**
     * Parameterized constructor for initializing a product with given attributes.
     * 
     * @param pid   The product ID.
     * @param pname The product name.
     * @param qty   The quantity of the product.
     * @param price The price of the product.
     */
    public Product(int pid, String pname, int qty, double price) {
        super();
        this.pid = pid;
        this.pname = pname;
        this.qty = qty;
        this.price = price;
    }

    /**
     * Gets the product ID.
     * 
     * @return the product ID.
     */
    public int getPid() {
        return pid;
    }

    /**
     * Sets the product ID.
     * 
     * @param pid The product ID to set.
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * Gets the product name.
     * 
     * @return the product name.
     */
    public String getPname() {
        return pname;
    }

    /**
     * Sets the product name.
     * 
     * @param pname The product name to set.
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * Gets the product quantity.
     * 
     * @return the quantity of the product.
     */
    public int getQty() {
        return qty;
    }

    /**
     * Sets the product quantity.
     * 
     * @param qty The quantity to set.
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * Gets the product price.
     * 
     * @return the price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     * 
     * @param price The price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the product.
     * 
     * @return A string containing the product details.
     */
    @Override
    public String toString() {
        return "Product [pid=" + pid + ", pname=" + pname + ", qty=" + qty + ", price=" + price + "]";
    }
}
