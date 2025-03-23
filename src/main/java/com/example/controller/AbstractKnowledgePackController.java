package com.example.controller;

import com.example.service.KnowledgePackService;
import com.example.utils.KPacConversionService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractKnowledgePackController {

    protected final KnowledgePackService knowledgePackService;
    protected final KPacConversionService kpacConversionService;
    protected final ObjectMapper mapper;

    public AbstractKnowledgePackController(KnowledgePackService knowledgePackService,
                                   KPacConversionService kpacConversionService,
                                   ObjectMapper mapper) {
        this.knowledgePackService = knowledgePackService;
        this.kpacConversionService = kpacConversionService;
        this.mapper = mapper;
    }

}
