package com.vromanyu.ws.repository;

import com.vromanyu.ws.entity.UserRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestLogRepository extends JpaRepository<UserRequestLog, Integer> {
}
