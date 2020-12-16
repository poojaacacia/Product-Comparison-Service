# Product-Comparison-Service
Project made with Spring boot and MongoDB

This service provides an endpoint to search for a product using it's name and category.
Additionally it also provides an endpoint which when triggered will load a particular data source file (TEXT) and load it into the service database(MongoDB).
The Mongodb data document structure is such that for a particular product in a category, there are embedded documents which will be website listings for the product (such as amazon,best buy etc). When an update comes to the product, the website listings are matched and the prices are updated.
--------------------------------------------------------------------------
ENDPOINTS

GET http://localhost:8080/relayr/product/category-and-name?category=&name=

POST http://localhost:8080/relayr/product/load-data?fileType=TEXT

GET http://localhost:8080/relayr/product/get-all

POST http://localhost:8080/relayr/product/add

----------------------------------------------------------------------

MongoDB document structure example

{
   "name": "Samsung Galaxy S20",
   "category": "Smartphone",
   "productInfo": [
      {
         "website": "snapdeal",
         "url": "http://snapdeal",
         "price": 4000
      },
      {
         "website": "Amazon.com",
         "url": "http://amazon.com/435783",
         "price": 7000
      }
   ]
}

--------------------------------------------------------------------------------

Datasource TEXT example


PRODUCT_NAME,PRODUCT_CATEGORY,WEBSITE,URL,PROCE,CURRENCY
Apple IPhone 11,Smartphone,Amazon.com,http://amazon.com/23648435,1000,USD
Samsung Galaxy S20,Smartphone,Amazon.com,http://amazon.com/435783,900,USD
Apple IPhone 11,Smartphone,BestBuy.com,http://BestBuy.com/23648435,1100,USD
Apple IPhone 11,Smartphone,Flipkart.com,http://flipkart.com/23648435,970,USD
LG LED TV,Television,Amazon.com,http://amazon.com/96435,3500,USD
Samsung Galaxy S20,Smartphone,Flipkart.com,http://flipkart.com/13245,1130,USD
