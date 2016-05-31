package org.example.ws.service;

import org.example.model.Account;

/**
 * Created by ThangN on 5/31/2016.
 */
public interface IAccountService {

    Account findByUsername(String username);

}
