package com.relayr.productcomparison.model;

import com.relayr.productcomparison.Constants;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductWebsiteDetails {

    private String productId;
    private String name;
    private String category;
    private String website;
    private String url;
    private BigDecimal price;
    private Constants.CURRENCY currency;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Constants.CURRENCY getCurrency() {
        return currency;
    }

    public void setCurrency(Constants.CURRENCY currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWebsiteDetails that = (ProductWebsiteDetails) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }
}
