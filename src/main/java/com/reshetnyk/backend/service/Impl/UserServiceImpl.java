package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.User;
import com.reshetnyk.backend.exception.UserProgressExistForUserException;
import com.reshetnyk.backend.exception.UserNotFoundException;
import com.reshetnyk.backend.repository.UserRepository;
import com.reshetnyk.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void update(Integer id, User uUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setUserName(uUser.getUserName());
        user.setFirstName(uUser.getFirstName());
        user.setMiddleName(uUser.getMiddleName());
        user.setLastName(uUser.getLastName());
        user.setEmail(uUser.getEmail());
        user.setPassword(uUser.getPassword());
        user.setPhoneNumber(uUser.getPhoneNumber());
        userRepository.save(uUser);
    }

    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if (!user.getUserProgresses().isEmpty()) throw new UserProgressExistForUserException(id, user.getUserProgresses());
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void dynamicProcedure(){
        userRepository.dynamicProcedure();
    }
}
