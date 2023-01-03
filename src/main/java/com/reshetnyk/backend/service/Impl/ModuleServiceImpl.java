package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.Module;
import com.reshetnyk.backend.exception.ModuleNotFoundException;
import com.reshetnyk.backend.repository.ModuleRepository;
import com.reshetnyk.backend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Module findById(Integer id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
    }

    @Override
    public Module create(Module module) {
        moduleRepository.save(module);
        return module;
    }

    @Override
    public void update(Integer id, Module uModule) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
        module.setName(uModule.getName());
        module.setText(uModule.getText());
        module.setAdditionalInformation(uModule.getAdditionalInformation());
        module.setOrderPosition(uModule.getOrderPosition());
        moduleRepository.save(module);
    }

    @Override
    public void delete(Integer id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(id));
        moduleRepository.delete(module);
    }
}

