package com.vromanyu.banking_app_ms.service;

import com.vromanyu.banking_app_ms.dto.AccountDto;
import com.vromanyu.banking_app_ms.entity.Account;
import com.vromanyu.banking_app_ms.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public AccountDto deposit(long id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        Account updatedAccount = accountRepository.save(account);
        return AccountDto.AccountMapper.toAccountDto(updatedAccount);
    }

    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (amount > account.getBalance()) {
            throw new RuntimeException("insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        Account updatedAccount = accountRepository.save(account);
        return AccountDto.AccountMapper.toAccountDto(updatedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(AccountDto.AccountMapper::toAccountDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAccountById(long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }
}
