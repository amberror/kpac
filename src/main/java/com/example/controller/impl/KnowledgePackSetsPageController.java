package com.example.controller.impl;

import com.example.controller.AbstractKnowledgeSetPackController;
import com.example.dto.KnowledgePackDTO;
import com.example.dto.KnowledgePackSetDTO;
import com.example.service.KnowledgePackService;
import com.example.service.KnowledgePackSetService;
import com.example.utils.KPacConversionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/sets")
public class KnowledgePackSetsPageController extends AbstractKnowledgeSetPackController {

    private final KnowledgePackService knowledgePackService;

    //Pages
    public static final String KPAC_SET_PAGE = "kpacSetPage";

    //Internal constants
    private static final String KPAC_SET_LIST_JSON = "kpacSetListJson";
    private static final String KPAC_LIST_JSON = "kpacListJson";

    @Autowired
    public KnowledgePackSetsPageController(KnowledgePackSetService knowledgePackSetService,
                                           KnowledgePackService knowledgePackService,
                                           KPacConversionService kpacConversionService,
                                           ObjectMapper mapper) {
        super(knowledgePackSetService, kpacConversionService, mapper);
        this.knowledgePackService = knowledgePackService;
    }

    @GetMapping
    public String getKPacSetPage(Model model) throws JsonProcessingException {
        List<KnowledgePackSetDTO> KPacSets = kpacConversionService.convertAll(knowledgePackSetService.getAll(), KnowledgePackSetDTO.class);
        List<KnowledgePackDTO> kPacs = kpacConversionService.convertAll(knowledgePackService.getAll(), KnowledgePackDTO.class);
        model.addAttribute(KPAC_SET_LIST_JSON, mapper.writeValueAsString(KPacSets));
        model.addAttribute(KPAC_LIST_JSON, kPacs);
        return KPAC_SET_PAGE;
    }
}
