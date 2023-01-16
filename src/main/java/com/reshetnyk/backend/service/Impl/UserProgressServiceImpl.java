package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.UserProgress;
import com.reshetnyk.backend.exception.UserProgressNotFoundException;
import com.reshetnyk.backend.repository.UserProgressRepository;
import com.reshetnyk.backend.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProgressServiceImpl implements UserProgressService {

    @Autowired
    UserProgressRepository userProgressRepository;

    @Override
    public List<UserProgress> findAll() {
        return userProgressRepository.findAll();
    }

    @Override
    public UserProgress findById(Integer id) {
        return userProgressRepository.findById(id)
                .orElseThrow(() -> new UserProgressNotFoundException(id));
    }

    @Override
    public UserProgress create(UserProgress userProgress) {
        userProgressRepository.save(userProgress);
        return userProgress;
    }

    @Override
    public void update(Integer id, UserProgress uUserProgress) {
        UserProgress userProgress = userProgressRepository.findById(id)
                .orElseThrow(() -> new UserProgressNotFoundException(id));
        userProgress.setBeginTimestamp(uUserProgress.getBeginTimestamp());
        userProgress.setEndTimestamp(uUserProgress.getEndTimestamp());
        userProgressRepository.save(userProgress);
    }

    @Override
    public void delete(Integer id) {
        UserProgress userProgress = userProgressRepository.findById(id)
                .orElseThrow(() -> new UserProgressNotFoundException(id));
        userProgressRepository.delete(userProgress);
    }
}
