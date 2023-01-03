package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.exception.AnswerOptionNotFoundException;
import com.reshetnyk.backend.repository.AnswerOptionRepository;
import com.reshetnyk.backend.service.AnswerOptionService;
import com.reshetnyk.backend.domain.AnswerOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {

    @Autowired
    AnswerOptionRepository answerOptionRepository;

    @Override
    public List<AnswerOption> findAll() {
        return answerOptionRepository.findAll();
    }

    @Override
    public AnswerOption findById(Integer id) {
        return answerOptionRepository.findById(id)
                .orElseThrow(() -> new AnswerOptionNotFoundException(id));
    }

    @Override
    public AnswerOption create(AnswerOption answerOption) {
        answerOptionRepository.save(answerOption);
        return answerOption;
    }

    @Override
    public void update(Integer id, AnswerOption uAnswerOption) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new AnswerOptionNotFoundException(id));
        answerOption.setOption(uAnswerOption.getOption());
        answerOption.setIsAnswer(uAnswerOption.getIsAnswer());
        answerOptionRepository.save(answerOption);
    }

    @Override
    public void delete(Integer id) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new AnswerOptionNotFoundException(id));
        answerOptionRepository.delete(answerOption);
    }
}
