package com.exam3.transfer3.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.exam3.transfer3.models.Account;
import com.exam3.transfer3.models.TransferForm;
import com.exam3.transfer3.repositories.AccountsRepository;
import com.exam3.transfer3.services.FundsTransferService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FundsTransferController {

    @Autowired
    private FundsTransferService fundsTransferService;
    
    private final AccountsRepository accountRepository;

    @Autowired
    public FundsTransferController(AccountsRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/")
    public String showTransferForm(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        model.addAttribute("transferForm", new TransferForm());
        return "index";
    }

    @PostMapping(path = "/transfer", headers = "Content-Type=application/json")
    public String transfer(@ModelAttribute("transferForm") @Valid TransferForm transferForm,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("accounts", accountRepository.findAll());
            model.addAttribute("errorMessage", "Please correct the errors below");
            return "index";
        }

        Account fromAccount = accountRepository.findByAccountId(transferForm.getFromAccountId());
        Account toAccount = accountRepository.findByAccountId(transferForm.getToAccountId());
        BigDecimal amount = transferForm.getAmount();

        if (fromAccount.getAccountId().equals(toAccount.getAccountId())) {
            model.addAttribute("accounts", accountRepository.findAll());
            model.addAttribute("errorMessage", "From account and to account cannot be the same");
            return "index";
        }

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            model.addAttribute("accounts", accountRepository.findAll());
            model.addAttribute("errorMessage", "From account balance is insufficient for this transfer");
            return "index";
        }

        accountRepository.transfer(fromAccount, toAccount, amount);

        return "success";
    }


    @RequestMapping("*") 
    public String invalidMtd() { 
    return "invalid"; 
    }

}
