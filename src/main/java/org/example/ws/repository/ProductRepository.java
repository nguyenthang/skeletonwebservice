package org.example.ws.repository;

import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Created by thangnguyen-imac on 5/28/16.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
