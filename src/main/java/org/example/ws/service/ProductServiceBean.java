package org.example.ws.service;

import org.example.model.Product;
import org.example.ws.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by thangnguyen-imac on 5/28/16.
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceBean implements IProductService {

    @Autowired
    private ProductRepository repository;

    /*private static BigInteger nextId;

    private static Map<BigInteger, Product> productMap;

    public static Product saveProduct(Product product){
        if(productMap == null){
            productMap = new HashMap<BigInteger, Product>();
            nextId = BigInteger.ONE;
        }


        if(product.getId() != null){
            Product product1 = productMap.get(product.getId());
            if(product1 == null){
                return null;
            }

            productMap.remove(product.getId());
            productMap.put(product.getId(), product);
            return product;
        }

        product.setId(nextId);
        nextId = nextId.add(BigInteger.ONE);
        productMap.put(product.getId(), product);

        return product;
    }

    static {
        Product p1 = new Product();
        p1.setName("Product1");
        p1.setDescription("This is product 1");
        saveProduct(p1);

        Product p2 = new Product();
        p2.setName("Product2");
        p2.setDescription("This is product 2");
        saveProduct(p2);
    }

    public boolean deleteProduct(BigInteger id){
        Product product = productMap.remove(id);
        if(product == null){
            return false;
        }

        return true;
    }*/

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @CachePut(value = "products", key = "#result.id")
    public Product create(Product product) {
        //Product product1 = saveProduct(product);
        if(product.getId() != null){
            return null;
        }

        Product product1 = repository.save(product);

        if (product1.getId() == 4){
            throw  new RuntimeException("Roll me back!");
        }

        return  repository.save(product);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void delete(Integer id) {
        //boolean deleted = deleteProduct(id);
        repository.delete(id);
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public Product findOne(Integer id) {
        //Product product = productMap.get(id);
        return repository.findOne(id);

    }

    @Override

    public Collection<Product> findAll() {
        //Collection<Product> products = productMap.values();
        return repository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @CachePut(value = "products", key = "#product.id")
    public Product update(Product product) {
        //Product product1 = saveProduct(product);
        Product product1 = this.findOne(product.getId());
        if(product1 == null){
            return null;
        }

        Product product2 = repository.save(product);

        //return product1;
        return product2;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void evictCache() {

    }
}
