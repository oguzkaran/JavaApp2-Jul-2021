package org.csystem.app.api.product.domain;

import org.csystem.app.pdb.repository.domain.AbstractEntity;

import javax.persistence.*;

/**
 * @author onder sahin
 */
@Entity
@Table(name = "t_products", uniqueConstraints = {@UniqueConstraint(columnNames = {"stock_number"})})
public class Product extends AbstractEntity {


    @Column(nullable = false)
    private String name;

    @Column(name = "stock_number", unique = true, nullable = false)
    private String stockNumber;

    @OneToOne(targetEntity = ProductCategory.class)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory category;

    @Column(nullable = false)
    private double stock;

    @Column(nullable = false)
    private double cost;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
