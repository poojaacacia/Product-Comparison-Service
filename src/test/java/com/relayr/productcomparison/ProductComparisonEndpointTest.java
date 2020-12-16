package com.relayr.productcomparison;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.util.List;

@SpringBootTest(classes = ProductComparisonApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductComparisonEndpointTest {

    private static final String getURL = "http://localhost:{port}/relayr/product/category-and-name/";
    private static final String loadURL = "http://localhost:{port}/relayr/product/load-data/";
    private static final String getAllURL = "http://localhost:{port}/relayr/product/get-all";

    @Value("${local.server.port}")
    int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testExistingData(){
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity(getURL+
                "?category={category}&name={name}",List.class,port,"Smartphone","Samsung Galaxy S20");
        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(1,responseEntity.getBody().size());
    }

    @Test
    public void testNonExistingData(){
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity(getURL+
                "?category={category}&name={name}",List.class,port,"Smartphone","Samsung Galaxy S10");
        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(0,responseEntity.getBody().size());
    }

    @Test
    public void testLoadDataTEXT(){
        ResponseEntity<Boolean> responseEntity =testRestTemplate.postForEntity(loadURL+
                "?fileType={type}",null, Boolean.class,port,"TEXT");
        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue((Boolean) responseEntity.getBody());
    }

    @Test
    public void testLoadDataCSV_NotImplemented(){
        Assertions.assertThrows(RuntimeException.class,() -> testRestTemplate.postForEntity(loadURL+
                "?fileType={type}",null,Boolean.class,port,"CSV"));
    }

    @Test
    public void testGetAllData(){
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity(getAllURL,List.class,port);
        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        Assertions.assertNotEquals(0,responseEntity.getBody().size());
    }

}
