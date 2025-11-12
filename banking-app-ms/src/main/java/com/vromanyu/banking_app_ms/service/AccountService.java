package com.vromanyu.banking_app_ms.service;

import com.vromanyu.banking_app_ms.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(long id);
}
