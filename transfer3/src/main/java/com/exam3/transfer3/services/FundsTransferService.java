package com.exam3.transfer3.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.exam3.transfer3.repositories.AccountsRepository;

public class FundsTransferService {

    // Transparently pushing the Repository through to the Controller (going through the Service)
    @Autowired
    private AccountsRepository accountRepository;
    
}
