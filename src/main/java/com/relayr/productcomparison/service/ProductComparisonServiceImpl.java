package com.relayr.productcomparison.service;

import com.relayr.productcomparison.Constants;
import com.relayr.productcomparison.ProductReader;
import com.relayr.productcomparison.model.Product;
import com.relayr.productcomparison.model.ProductWebsiteDetails;
import com.relayr.productcomparison.repository.ProductMongoRepository;
import com.relayr.productcomparison.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class ProductComparisonServiceImpl implements ProductComparisonService{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMongoRepository productMongoRepository;

    @Override
    public List<Product> getProductUsingCategoryAndName(String category, String name) {
        return productRepository.getProductUsingCategoryAndName(category,name);
    }
    /**
     * This method loads the data based on the file type(data source) that is triggered in the endpoint
     * ProductReader is a functional interface which parse the file based on the data source type
     * @param fileType Type of data source (TEXT source is currently implemented)
     * @return Boolean true if operation is successful
     */
    @Override
    public boolean loadDataByFileType(String fileType) throws IOException {
        List<ProductWebsiteDetails>
        productWebsiteDetailsList= ProductReader.readFile(Constants.FILE_TYPE.valueOf(fileType));

        Map<ProductWebsiteDetails,List<ProductWebsiteDetails>>
                productWebsiteDetailsListMap= new HashMap<>();

        productWebsiteDetailsList.forEach(productWebsiteDetails -> {
            List<ProductWebsiteDetails> productWebsiteDetails1=
                    productWebsiteDetailsListMap.getOrDefault(productWebsiteDetails, new ArrayList<>());
            productWebsiteDetails1.add(productWebsiteDetails);
            productWebsiteDetailsListMap.put(productWebsiteDetails,productWebsiteDetails1);
        });
        productRepository.putOrUpdateDatabaseUsingMongoRepo(productWebsiteDetailsListMap);
        return true;

    }

    @Override
    public Boolean addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    public List<Product> getAllProductsInfo() {
        return productMongoRepository.findAll();
    }
}
