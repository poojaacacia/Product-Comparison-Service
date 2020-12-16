package com.relayr.productcomparison;

import com.relayr.productcomparison.model.Product;
import com.relayr.productcomparison.model.ProductInfo;
import com.relayr.productcomparison.model.ProductWebsiteDetails;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    public static ProductWebsiteDetails getProductWebsiteDetails(String[] productAttributes) {
        ProductWebsiteDetails productWebsiteDetails = new ProductWebsiteDetails();
        productWebsiteDetails.setName(productAttributes[0]);
        productWebsiteDetails.setCategory(productAttributes[1]);
        productWebsiteDetails.setWebsite(productAttributes[2]);
        productWebsiteDetails.setUrl(productAttributes[3]);
        productWebsiteDetails.setPrice(new BigDecimal(productAttributes[4]));
        productWebsiteDetails.setCurrency(Constants.CURRENCY.valueOf(productAttributes[5]));
        return productWebsiteDetails;
    }

    public static Product mapToProduct(List<ProductWebsiteDetails> productWebsiteDetails,
                                       Product productFromDb)  {
        Product product = new Product();
        Set<ProductInfo> productInfoSet =null;
        if(productFromDb !=null) {
            product=productFromDb;
            productInfoSet = productFromDb.getProductInfo();
        }else {
            productInfoSet = new HashSet<>();
            product.setName(productWebsiteDetails.get(0).getName());
            product.setCategory(productWebsiteDetails.get(0).getCategory());
        }
        for(ProductWebsiteDetails productWebsiteDetail:productWebsiteDetails){
            ProductInfo productInfo = new ProductInfo();
            productInfo.setWebsite(productWebsiteDetail.getWebsite());
            productInfo.setUrl(productWebsiteDetail.getUrl());
            productInfo.setPrice(productWebsiteDetail.getPrice());
            if(!productInfoSet.add(productInfo)) {
                productInfoSet.remove(productInfo);
                productInfoSet.add(productInfo);
            }
        }
        product.setProductInfo(productInfoSet);
        return product;
    }
}
