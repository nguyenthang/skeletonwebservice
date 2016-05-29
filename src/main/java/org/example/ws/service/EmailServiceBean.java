package org.example.ws.service;

import org.example.model.Product;
import org.example.ws.util.AsyncResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by thangnguyen-imac on 5/29/16.
 */
@Service
public class EmailServiceBean implements IEmailService {


    @Autowired
    private IProductService productService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean send(Product product) {
        logger.info("> send");

        Boolean success = Boolean.FALSE;

        long pause = 5000;

        try {
            Thread.sleep(pause);
        }catch (Exception e){

        }

        logger.info("Processing time was {} seconds", pause / 1000);
        success = Boolean.TRUE;
        logger.info("< send");
        return success;
    }

    @Override
    @Async
    public void sendAsync(Product product) {

        logger.info("> Async");
        try {
            send(product);
        }catch (Exception e){
            logger.info("Exception caught sending... ");
        }

        logger.info("< Async");
    }

    @Override
    public Future<Boolean> sendAsyncWithResult(Product product) {

        logger.info("> sendAsync");
        AsyncResponse<Boolean> response = new AsyncResponse<Boolean>();

        try {
            Boolean success = this.send(product);
            response.complete(success);
        }catch (Exception e){

        }

        logger.info("< sendAsync");
        return response;
    }
}
