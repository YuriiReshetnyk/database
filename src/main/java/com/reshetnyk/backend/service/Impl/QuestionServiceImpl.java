package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.Question;
import com.reshetnyk.backend.exception.QuestionNotFoundException;
import com.reshetnyk.backend.exception.AnswerOptionsExistForTestException;
import com.reshetnyk.backend.repository.QuestionRepository;
import com.reshetnyk.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findById(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @Transactional
    public Question create(Question question) {
        questionRepository.save(question);
        return question;
    }

    @Transactional
    public void update(Integer id, Question uQuestion) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        question.setQuestion(uQuestion.getQuestion());
        question.setPhoto(uQuestion.getPhoto());
        question.setTest(uQuestion.getTest());
        questionRepository.save(question);
    }

    @Transactional
    public void delete(Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        if (!question.getAnswerOptions().isEmpty()) throw new AnswerOptionsExistForTestException(id, question.getAnswerOptions());
        questionRepository.delete(question);
    }
}
