package com.relayr.productcomparison.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "products")
public class Product {
    @Id
    private ObjectId id;
    private String name;
    private String category;
    private Set<ProductInfo> productInfo;

    public Product() {
    }

    public Product(String name, String category, Set<ProductInfo> productInfo) {
        this.name = name;
        this.category = category;
        this.productInfo = productInfo;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(Set<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }
}
