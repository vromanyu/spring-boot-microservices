package com.vromanyu.banking_app_ms.dto;

import com.vromanyu.banking_app_ms.entity.Account;

public record AccountDto(Long id, String accountHolderName, double balance) {

    public static final class AccountMapper {

        public static Account toAccount(AccountDto accountDto){
            Account account = new Account();
            account.setId(account.getId());
            account.setAccountHolderName(accountDto.accountHolderName());
            account.setBalance(accountDto.balance());
            return account;
        }

        public static AccountDto toAccountDto(Account account){
            return new AccountDto(account.getId(), account.getAccountHolderName(), account.getBalance());
        }

    }
}
