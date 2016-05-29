package org.example.ws.service;

import org.example.model.Product;
import java.util.concurrent.Future;
/**
 * Created by thangnguyen-imac on 5/29/16.
 */
public interface IEmailService {

    boolean send(Product product);

    void sendAsync(Product product);


    Future<Boolean> sendAsyncWithResult(Product product);
}
