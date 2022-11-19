package edu.festu.ivankuznetsov.securitydemo.service;

import edu.festu.ivankuznetsov.securitydemo.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    AccountService(AccountRepository repository){
        accountRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = accountRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User does not exist");}
        else
            return user.get();
    }
}
