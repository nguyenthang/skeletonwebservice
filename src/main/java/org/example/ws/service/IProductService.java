package org.example.ws.service;

import org.example.model.Product;

import java.math.BigInteger;
import java.util.Collection;

/**
 * Created by thangnguyen-imac on 5/28/16.
 */
public interface IProductService {

    Product create(Product product);

    void delete(Integer id);

    Product findOne(Integer id);

    Collection<Product> findAll();

    Product update(Product product);

    void evictCache();
}
