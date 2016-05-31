package org.example.ws.batch;

import org.example.model.Product;
import org.example.ws.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by thangnguyen-imac on 5/29/16.
 */

@Profile(value = "batch")
@Component
public class ProductBatchBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IProductService productService;

    //@Scheduled(cron = "0,30 * * * * *")
    public void cronJob(){
        logger.info("> cronjob");

        Collection<Product> products = productService.findAll();

        logger.info("There are {} products in the data store", products.size());
        logger.info("< cronjob");
    }

    //@Scheduled(initialDelay = 5000, fixedDelay = 15000)
   // @Scheduled(initialDelayString = "${batch.product.initialdelay}", fixedDelayString = "${batch.product.fixeddelay}")
    public void fixedRateInitialSchedule(){

        logger.info("> Initial fixedRate");

        long pause = 5000;
        long start = System.currentTimeMillis();
        do {
            if((start + pause) < System.currentTimeMillis()){
                break;
            }
        }while (true);

        logger.info("Processing {}", (pause / 1000));
        logger.info("> Initial fixedRate");
    }
}
