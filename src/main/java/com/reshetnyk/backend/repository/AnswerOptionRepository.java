package com.reshetnyk.backend.repository;

import com.reshetnyk.backend.domain.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Integer> {
}