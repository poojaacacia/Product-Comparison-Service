package com.relayr.productcomparison.repository;

import com.relayr.productcomparison.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductMongoRepository extends MongoRepository<Product, String> {

    Product findOneByCategoryAndName(String category,String name);
    List<Product> findByNameAndCategory(String name,String Category);
    List<Product> findByName(String name);
    List<Product> findByCategory(String category);
}
