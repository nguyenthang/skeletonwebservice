package org.example.ws.service;

import org.example.model.Account;
import org.example.ws.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ThangN on 5/31/2016.
 */
@Service
public class AccountServiceBean implements IAccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public Account findByUsername(String username) {
        Account account = repository.findByUsername(username);
        return account;
    }
}
