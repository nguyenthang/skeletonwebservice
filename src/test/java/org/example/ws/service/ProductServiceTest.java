package org.example.ws.service;

import org.example.model.Product;
import org.example.ws.AbstractTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by thangnguyen-imac on 5/29/16.
 */

@Transactional
public class ProductServiceTest extends AbstractTest {

    @Autowired
    private IProductService productService;

    @Before
    public void setup(){
        productService.evictCache();
    }

    @After
    public void destroy(){

    }

    @Test
    public void testFindAll(){
        Collection<Product> products = productService.findAll();

        Assert.assertNotNull("failure - expected not null ", products);
        Assert.assertEquals("failure - expected size ",2, products.size());
    }

    @Test
    public void testFindOne(){
        Integer id = new Integer(1);

        Product entity = productService.findOne(id);
        Assert.assertNotNull("failure - expected", entity);
    }
}
