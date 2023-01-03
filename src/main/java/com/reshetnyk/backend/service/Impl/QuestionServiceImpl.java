package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.Question;
import com.reshetnyk.backend.exception.QuestionNotFoundException;
import com.reshetnyk.backend.exception.AnswerOptionsExistForTestException;
import com.reshetnyk.backend.repository.QuestionRepository;
import com.reshetnyk.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @Override
    public Question create(Question question) {
        questionRepository.save(question);
        return question;
    }

    @Override
    public void update(Integer id, Question uQuestion) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        question.setQuestion(uQuestion.getQuestion());
        question.setPhoto(uQuestion.getPhoto());
        questionRepository.save(question);
    }

    @Override
    public void delete(Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        if (!question.getAnswerOptions().isEmpty()) throw new AnswerOptionsExistForTestException(id, question.getAnswerOptions());
        questionRepository.delete(question);
    }
}
