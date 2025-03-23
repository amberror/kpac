package com.example.controller;

import com.example.service.KnowledgePackSetService;
import com.example.utils.KPacConversionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractKnowledgeSetPackController {
    protected final KnowledgePackSetService knowledgePackSetService;
    protected final KPacConversionService kpacConversionService;
    protected final ObjectMapper mapper;

    public AbstractKnowledgeSetPackController(KnowledgePackSetService knowledgePackSetService,
                                          KPacConversionService kpacConversionService,
                                          ObjectMapper mapper) {
        this.knowledgePackSetService = knowledgePackSetService;
        this.kpacConversionService = kpacConversionService;
        this.mapper = mapper;
    }
}
