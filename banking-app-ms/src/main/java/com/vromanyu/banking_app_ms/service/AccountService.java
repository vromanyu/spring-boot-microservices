package com.vromanyu.banking_app_ms.service;

import com.vromanyu.banking_app_ms.controller.AccountController;
import com.vromanyu.banking_app_ms.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(long id);
    AccountDto deposit(long id, double amount);
}
