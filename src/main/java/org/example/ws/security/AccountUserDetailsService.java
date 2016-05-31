package org.example.ws.security;

import org.example.model.Account;
import org.example.model.Role;
import org.example.ws.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ThangN on 5/31/2016.
 */
@Service
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    private IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        if(account == null){
            return null;
        }

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Role role : account.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        User userDetails = new User(account.getUsername(), account.getPassword(), account.isEnable(), account.isExpired(), account.isCredentialExpired(), account.isLocked(), authorities);

        return userDetails;
    }
}
