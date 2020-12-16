package com.relayr.productcomparison.service;

import com.relayr.productcomparison.model.Product;
import java.io.IOException;
import java.util.List;

public interface ProductComparisonService {

    List<Product> getProductUsingCategoryAndName(String category,String name);
    boolean loadDataByFileType(String fileType) throws IOException;
    Boolean addProduct(Product product);
    List<Product>  getAllProductsInfo();
}
