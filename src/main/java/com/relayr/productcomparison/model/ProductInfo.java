package com.relayr.productcomparison.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductInfo {
    private String website;
    private String url;
    private BigDecimal price;

    public ProductInfo() {
    }
    public ProductInfo(String website, String url, BigDecimal price) {
        super();
        this.website = website;
        this.url = url;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfo that = (ProductInfo) o;
        return website.equals(that.website) &&
                url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(website, url);
    }
}
