package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.Test;
import com.reshetnyk.backend.exception.QuestionExistForTestException;
import com.reshetnyk.backend.exception.UserProgressExistForTestException;
import com.reshetnyk.backend.exception.TestNotFoundException;
import com.reshetnyk.backend.repository.TestRepository;
import com.reshetnyk.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public Test findById(Integer id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(id));
    }

    @Transactional
    public Test create(Test test) {
        testRepository.save(test);
        return test;
    }

    @Transactional
    public void update(Integer id, Test uTest) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(id));
        test.setName(uTest.getName());
        test.setIntroduction(uTest.getIntroduction());
        test.setOrderPosition(uTest.getOrderPosition());
        test.setCourse(uTest.getCourse());
        testRepository.save(test);
    }

    @Transactional
    public void delete(Integer id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException(id));
        if (!test.getQuestions().isEmpty()) throw new QuestionExistForTestException(id, test.getQuestions());
        if (!test.getUserProgresses().isEmpty()) throw new UserProgressExistForTestException(id, test.getUserProgresses());
        testRepository.delete(test);
    }
}
