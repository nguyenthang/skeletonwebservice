package org.example.ws.repository;

import org.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ThangN on 5/31/2016.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByUsername(String username);

}
