package com.reshetnyk.backend.service;

import com.reshetnyk.backend.domain.User;
import org.springframework.data.jpa.repository.query.Procedure;

public interface UserService extends GeneralService<User, Integer> {
    void dynamicProcedure();
}