package com.reshetnyk.backend.controller;

import com.reshetnyk.backend.domain.Topic;
import com.reshetnyk.backend.dto.TopicDto;
import com.reshetnyk.backend.dto.assembler.TopicDtoAssembler;
import com.reshetnyk.backend.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicDtoAssembler topicDtoAssembler;

    @GetMapping(value = "/{topicId}")
    public ResponseEntity<TopicDto> getTopic(@PathVariable Integer topicId) {
        Topic topic = topicService.findById(topicId);
        TopicDto topicDto = topicDtoAssembler.toModel(topic);
        return new ResponseEntity<>(topicDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TopicDto>> getAllTopics() {
        List<Topic> topics = topicService.findAll();
        CollectionModel<TopicDto> topicDtos = topicDtoAssembler.toCollectionModel(topics);
        return new ResponseEntity<>(topicDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TopicDto> addTopic(@RequestBody Topic topic) {
        Topic topics = topicService.create(topic);
        TopicDto topicDto = topicDtoAssembler.toModel(topics);
        return new ResponseEntity<>(topicDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{topicId}")
    public ResponseEntity<?> updateTopic(@RequestBody Topic uTopic, @PathVariable Integer topicId) {
        topicService.update(topicId, uTopic);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable Integer topicId) {
        topicService.delete(topicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
