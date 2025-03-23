package com.example.controller.impl;

import com.example.controller.AbstractKnowledgePackController;
import com.example.dto.KnowledgePackDTO;
import com.example.model.KnowledgePackModel;
import com.example.service.KnowledgePackService;
import com.example.utils.KPacConversionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kpacs")
public class KnowledgePackController extends AbstractKnowledgePackController {

    @Autowired
    public KnowledgePackController(KnowledgePackService knowledgePackService,
                                   KPacConversionService kpacConversionService,
                                   ObjectMapper mapper) {
        super(knowledgePackService, kpacConversionService, mapper);
    }


    //!! This is async alternative of endpoint /kpacs/add in KnowledgePackPageController
    /*
    @PostMapping("/add")
    public ResponseEntity<String> addKPac(@Valid @RequestBody KnowledgePackDTO dto) throws JsonProcessingException {
        //DTO Validation
        KnowledgePackDTO resultDto = kpacConversionService.convert(
                knowledgePackService.create(
                        kpacConversionService.convert(dto, KnowledgePackModel.class)),
                KnowledgePackDTO.class);
        return ResponseEntity.ok(mapper.writeValueAsString(resultDto));
    }*/


    //!! This is async alternative of endpoint /kpacs/delete in KnowledgePackPageController
    /*
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteKPac(@RequestParam("id") @NotNull @Min(1L) Long id) {
        knowledgePackService.delete(kpacConversionService.convert(KnowledgePackDTO.builder().id(id).build(), KnowledgePackModel.class));
        return ResponseEntity.ok().build();
    }*/
}