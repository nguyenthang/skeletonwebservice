package org.example.web.api;

import org.example.model.Product;
import org.example.ws.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by thangnguyen-imac on 5/27/16.
 */

@RestController
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/api/products",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Product>> getProducts(){

        Collection<Product> products = productService.findAll();
        logger.info("Size of product: " + products.size());
        return new ResponseEntity<Collection<Product>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/product/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") Integer id){
        Product product = productService.findOne(id);

        if(product == null){
            return  new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/products", method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product product1 = productService.create(product);

        return new ResponseEntity<Product>(product1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/product/{id}", method = RequestMethod.PUT,
                    consumes =  MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product product1 = productService.update(product);

        if(product == null){
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  new ResponseEntity<Product>(product1, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/product/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id, @RequestBody Product product){

        productService.delete(id);

        //logger.info("Delete : " + deleted);
        //if(!deleted){
//            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
  //      }

        return new ResponseEntity<Product>(HttpStatus.OK);
    }
}
