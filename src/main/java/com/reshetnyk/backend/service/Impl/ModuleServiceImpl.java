package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.Module;
import com.reshetnyk.backend.exception.ModuleNotFoundException;
import com.reshetnyk.backend.repository.ModuleRepository;
import com.reshetnyk.backend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    public Module findById(Integer id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
    }

    @Transactional
    public Module create(Module module) {
        moduleRepository.save(module);
        return module;
    }

    @Transactional
    public void update(Integer id, Module uModule) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
        module.setName(uModule.getName());
        module.setText(uModule.getText());
        module.setVideo(uModule.getVideo());
        module.setAdditionalInformation(uModule.getAdditionalInformation());
        module.setOrderPosition(uModule.getOrderPosition());
        module.setCourse(uModule.getCourse());
        moduleRepository.save(module);
    }

    @Transactional
    public void delete(Integer id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
        moduleRepository.delete(module);
    }
}

