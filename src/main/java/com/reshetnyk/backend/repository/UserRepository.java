package com.reshetnyk.backend.repository;

import com.reshetnyk.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Procedure("dynamic_procedure")
    void dynamicProcedure();
}