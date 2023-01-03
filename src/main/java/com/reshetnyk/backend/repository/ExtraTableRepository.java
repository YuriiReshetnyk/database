package com.reshetnyk.backend.repository;

import com.reshetnyk.backend.domain.ExtraTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraTableRepository extends JpaRepository<ExtraTable, Integer> {
}