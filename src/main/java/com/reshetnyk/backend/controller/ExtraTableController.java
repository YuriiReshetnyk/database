package com.reshetnyk.backend.controller;

import com.reshetnyk.backend.domain.ExtraTable;
import com.reshetnyk.backend.dto.ExtraTableDto;
import com.reshetnyk.backend.dto.assembler.ExtraTableDtoAssembler;
import com.reshetnyk.backend.service.ExtraTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/extraTable")
public class ExtraTableController {
    @Autowired
    private ExtraTableService extraTableService;
    @Autowired
    private ExtraTableDtoAssembler extraTableDtoAssembler;

    @GetMapping(value = "/{extraTableId}")
    public ResponseEntity<ExtraTableDto> getExtraTable(@PathVariable Integer extraTableId) {
        ExtraTable extraTable = extraTableService.findById(extraTableId);
        ExtraTableDto extraTableDto = extraTableDtoAssembler.toModel(extraTable);
        return new ResponseEntity<>(extraTableDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ExtraTableDto>> getAllExtraTables() {
        List<ExtraTable> extraTables = extraTableService.findAll();
        CollectionModel<ExtraTableDto> extraTableDtos = extraTableDtoAssembler.toCollectionModel(extraTables);
        return new ResponseEntity<>(extraTableDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ExtraTableDto> addExtraTable(@RequestBody ExtraTable extraTable) {
        ExtraTable extraTables = extraTableService.create(extraTable);
        ExtraTableDto extraTableDto = extraTableDtoAssembler.toModel(extraTables);
        return new ResponseEntity<>(extraTableDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{extraTableId}")
    public ResponseEntity<?> updateExtraTable(@RequestBody ExtraTable uExtraTable, @PathVariable Integer extraTableId) {
        extraTableService.update(extraTableId, uExtraTable);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{extraTableId}")
    public ResponseEntity<?> deleteTopic(@PathVariable Integer extraTableId) {
        extraTableService.delete(extraTableId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
