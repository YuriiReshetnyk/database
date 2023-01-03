package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.Topic;
import com.reshetnyk.backend.exception.CourseExistForTopicException;
import com.reshetnyk.backend.exception.TopicNotFoundException;
import com.reshetnyk.backend.repository.TopicRepository;
import com.reshetnyk.backend.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findById(Integer id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));
    }

    @Override
    public Topic create(Topic Topic) {
        topicRepository.save(Topic);
        return Topic;
    }

    @Override
    public void update(Integer id, Topic uTopic) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));
        topic.setName(uTopic.getName());
        topicRepository.save(topic);
    }

    @Override
    public void delete(Integer id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));
        if (!topic.getCourses().isEmpty()) throw new CourseExistForTopicException(id, topic.getCourses());
        topicRepository.delete(topic);
    }
}
