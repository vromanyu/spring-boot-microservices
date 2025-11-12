package com.vromanyu.banking_app_ms.service;

import com.vromanyu.banking_app_ms.dto.AccountDto;
import com.vromanyu.banking_app_ms.entity.Account;
import com.vromanyu.banking_app_ms.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account savedAccount = accountRepository.save(AccountDto.AccountMapper.toAccount(accountDto));
        return AccountDto.AccountMapper.toAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id) {
        return AccountDto.AccountMapper
                .toAccountDto(
                        accountRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException("Account not found")));
    }
}
