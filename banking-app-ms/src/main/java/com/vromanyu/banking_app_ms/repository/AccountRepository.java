package com.vromanyu.banking_app_ms.repository;

import com.vromanyu.banking_app_ms.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
}
