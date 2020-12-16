package com.relayr.productcomparison.interaction;

import com.relayr.productcomparison.model.Product;
import com.relayr.productcomparison.service.ProductComparisonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("relayr")
public class ProductComparisonEndpoint {

    @Autowired
    ProductComparisonService productComparisonService;

    private static final Logger logger = LoggerFactory.getLogger(ProductComparisonEndpoint.class);

    /**
     * This endpoint method matches the category and name of product with the products list present
     * in the service database.
     * It'll return all matching products name if category is not provided and all matching product's
     * category if name is not provided. It'll return all products if no category or name is provided
     * @param category Product's category
     * @param  name Product's full name
     * @return List<Product>  of matching products
     */
    @RequestMapping("product/category-and-name")
    @GetMapping
    public List<Product> getProductForCategoryAndName(@QueryParam("category") String category,
                                                 @QueryParam("name") String name){
        logger.info("Received get request for category :{} and name : {}",category,name);
        return productComparisonService.getProductUsingCategoryAndName(category,name);
    }

    /**
     * This endpoint method matches the file type given in the query param with the type of data source
     * that is currently implemented in the system.
     * It'll then read the file and load that data after converting it into proper document into the mongodb
     * @param fileType type of file to be parsed and loaded (TEXT,CSV,XML)
     * @return Boolean true if loading service database is successful
     */
    @PostMapping
    @RequestMapping("product/load-data")
    public Boolean loadDataIntoDb(String fileType ) throws IOException {
        logger.info("Received request to load {} type data source " , fileType);

        return productComparisonService.loadDataByFileType(fileType);
    }

    /**
     * This endpoint method adds the product to service database (mongodb)
     * It'll update the product (with updated pricing or website information)
     * if the product is already present in the database
     * @param product json form of Product object
     * @return Boolean true if operation is successful
     */
    @PostMapping
    @RequestMapping("product/add")
    public Boolean addProduct(@RequestBody Product product ) {
        return productComparisonService.addProduct(product);
    }
    /**
     * This endpoint will fetch all the products currently present in the service database
     * @return List of all products currently present in the database
     */
    @GetMapping
    @RequestMapping("product/get-all")
    public List<Product>  getAllProductsInfo() {
        return productComparisonService.getAllProductsInfo();
    }

    @GetMapping
    @RequestMapping("/")
    public String getmessage() {
        return "hello world";
    }

}
