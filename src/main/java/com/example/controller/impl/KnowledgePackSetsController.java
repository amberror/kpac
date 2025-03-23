package com.example.controller.impl;

import com.example.controller.AbstractKnowledgeSetPackController;
import com.example.dto.KnowledgePackSetDTO;
import com.example.model.KnowledgePackSetModel;
import com.example.service.KnowledgePackSetService;
import com.example.utils.KPacConversionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sets")
public class KnowledgePackSetsController extends AbstractKnowledgeSetPackController {

    @Autowired
    public KnowledgePackSetsController(KnowledgePackSetService knowledgePackSetService,
                                       KPacConversionService kpacConversionService,
                                       ObjectMapper mapper) {
        super(knowledgePackSetService, kpacConversionService, mapper);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addKPacSet(@Valid @RequestBody KnowledgePackSetDTO dto, BindingResult validationResult) throws JsonProcessingException {

        if(validationResult.hasErrors()) {
            return ResponseEntity
                    .status(400)
                    .body(validationResult.getAllErrors().stream()
                            .map(ObjectError::toString)
                            .collect(Collectors.joining("\n")));
        }

        KnowledgePackSetDTO resultDto = kpacConversionService.convert(
                knowledgePackSetService.create(
                        kpacConversionService.convert(dto, KnowledgePackSetModel.class)),
                KnowledgePackSetDTO.class);
        return ResponseEntity.ok(mapper.writeValueAsString(resultDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteKPacSet(@RequestParam("id") Long id) {
        knowledgePackSetService.delete(kpacConversionService.convert(KnowledgePackSetDTO.builder().id(id).kpacs(Set.of()).build(), KnowledgePackSetModel.class));
        return ResponseEntity.ok().build();
    }
}
