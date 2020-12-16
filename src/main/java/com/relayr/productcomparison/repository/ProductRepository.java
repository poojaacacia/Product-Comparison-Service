package com.relayr.productcomparison.repository;

import com.relayr.productcomparison.model.Product;
import com.relayr.productcomparison.model.ProductWebsiteDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    List<Product> getProductUsingCategoryAndName(String category, String name);

    void putOrUpdateDatabaseUsingMongoRepo(Map<ProductWebsiteDetails, List<ProductWebsiteDetails>> productWebsiteDetailsListMap);

    boolean addProduct(Product product);
}
