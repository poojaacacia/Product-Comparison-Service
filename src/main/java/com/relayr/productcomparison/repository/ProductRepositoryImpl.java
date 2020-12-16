package com.relayr.productcomparison.repository;

import com.relayr.productcomparison.model.Product;
import com.relayr.productcomparison.model.ProductInfo;
import com.relayr.productcomparison.model.ProductWebsiteDetails;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.relayr.productcomparison.Utils.mapToProduct;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    @Autowired
    private ProductMongoRepository productMongoRepository;

    /**
     * This repository method uses spring data for MongoDb to use specific aggregated methods
     * to query the document database for specific products.
     * It'll return all matching products name if category is not provided and all matching product's
     * category if name is not provided. It'll return all products if no category or name is provided
     * @param category Product's category
     * @param  name Product's full name
     * @return List<Product>  of matching products
     */
    @Override
    public List<Product> getProductUsingCategoryAndName(String category, String name){
        List<Product> productList =null;
        if(StringUtils.isNotEmpty(category) && StringUtils.isNotEmpty(name)) {
            productList = productMongoRepository.findByNameAndCategory(name,
                    category);
        }else if(StringUtils.isNotEmpty(name)){
            productList = productMongoRepository.findByName(name);
        }else if(StringUtils.isNotEmpty(category)){
            productList = productMongoRepository.findByCategory(
                    category);
        }else{
            productList = productMongoRepository.findAll();
        }
        logger.debug(" Fetched and returning product list :{}",productList);
        return productList;
    }
    /**
     * This method inserts a new product if the name and category of the product doesn't match the existing product
     * it'll update the product if the name and category matches an existing product.
     * In case a website listing is already present for the product
     * in the database, it'll update the price, else it'll add the new website listing and price
     * @param productWebsiteDetailsListMap map where key is a unique product
     *                                     (category and name) and values is a list of all
     *                                     the rows with that product and website information.
     * @return Boolean true if operation is successful
     */
    @Override
    public void putOrUpdateDatabaseUsingMongoRepo(Map<ProductWebsiteDetails,
            List<ProductWebsiteDetails>> productWebsiteDetailsListMap) {
        List<Product> newProductsList = new ArrayList<>();
        productWebsiteDetailsListMap.values().forEach(productWebsiteDetailsList -> {
            String category = productWebsiteDetailsList.get(0).getCategory();
            String name = productWebsiteDetailsList.get(0).getName();
            List<Product> productList = productMongoRepository.findByNameAndCategory(name,
                    category);
            Product oldProduct = null;

            if(!productList.isEmpty()) {
                oldProduct = productList.get(0);
            }
            Product newProduct= mapToProduct(productWebsiteDetailsList,oldProduct);
            newProductsList.add(newProduct);
        });
        productMongoRepository.saveAll(newProductsList);
    }
    /**
     * This method inserts a new product or update an existing product
     * @param product product receive
     * @return boolean true if operation is successful
     */
    @Override
    public boolean addProduct(Product product) {
        List<Product> productList = productMongoRepository.findByNameAndCategory(product.getName(),
                product.getCategory());
        if(!productList.isEmpty()){
            Set<ProductInfo> productInfoSet = productList.get(0).getProductInfo();
            product.getProductInfo().forEach(productInfo -> {
                if(!productInfoSet.add(productInfo)) {
                    productInfoSet.remove(productInfo);
                    productInfoSet.add(productInfo);
                }
            });
            product=productList.get(0);
        }
        productMongoRepository.save(product);
        return true;
    }

}
