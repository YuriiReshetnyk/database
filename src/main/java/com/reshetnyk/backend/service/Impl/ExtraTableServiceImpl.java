package com.reshetnyk.backend.service.Impl;

import com.reshetnyk.backend.domain.ExtraTable;
import com.reshetnyk.backend.exception.ExtraTableNotFoundException;
import com.reshetnyk.backend.repository.ExtraTableRepository;
import com.reshetnyk.backend.service.ExtraTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExtraTableServiceImpl implements ExtraTableService {

    @Autowired
    ExtraTableRepository extraTableRepository;

    public List<ExtraTable> findAll() {
        return extraTableRepository.findAll();
    }

    public ExtraTable findById(Integer id) {
        return extraTableRepository.findById(id)
                .orElseThrow(() -> new ExtraTableNotFoundException(id));
    }

    @Transactional
    public ExtraTable create(ExtraTable extraTable) {
        extraTableRepository.save(extraTable);
        return extraTable;
    }

    @Transactional
    public void update(Integer id, ExtraTable uExtraTable) {
        ExtraTable extraTable = extraTableRepository.findById(id)
                .orElseThrow(() -> new ExtraTableNotFoundException(id));
        extraTable.setIntColum(uExtraTable.getIntColum());
        extraTable.setStringColum(uExtraTable.getStringColum());
        extraTable.setUser(uExtraTable.getUser());
        extraTableRepository.save(extraTable);
    }

    @Transactional
    public void delete(Integer id) {
        ExtraTable extraTable = extraTableRepository.findById(id)
                .orElseThrow(() -> new ExtraTableNotFoundException(id));
        extraTableRepository.delete(extraTable);
    }
}