package com.vromanyu.banking_app_ms.controller;

import com.vromanyu.banking_app_ms.dto.AccountDto;
import com.vromanyu.banking_app_ms.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        AccountDto createdAccount = accountService.createAccount(accountDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(createdAccount.id()).toUri();
        return ResponseEntity.created(uri).body(createdAccount);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping(value = "/{id}/deposit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> amount){
        return ResponseEntity.ok(accountService.deposit(id, amount.get("amount")));
    }
}
