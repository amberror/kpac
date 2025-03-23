package com.example.controller.impl;

import com.example.controller.AbstractKnowledgePackController;
import com.example.dto.KnowledgePackDTO;
import com.example.model.KnowledgePackModel;
import com.example.service.KnowledgePackService;
import com.example.service.KnowledgePackSetService;
import com.example.utils.KPacConversionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/kpacs")
public class KnowledgePackPageController extends AbstractKnowledgePackController {

    private final KnowledgePackSetService knowledgePackSetService;

    //Pages
    public static final String KPAC_PAGE = "kpacPage";

    //Internal constants
    private static final String KPAC_LIST_JSON = "kpacListJson";
    private static final String VALIDATION_ERRORS = "validationErrors";
    private static final String REDIRECT_KPAC_PAGE = "redirect:/kpacs";

    @Autowired
    public KnowledgePackPageController(KnowledgePackService knowledgePackService,
                                       KnowledgePackSetService knowledgePackSetService,
                                       KPacConversionService kpacConversionService,
                                       ObjectMapper mapper) {
        super(knowledgePackService, kpacConversionService, mapper);
        this.knowledgePackSetService = knowledgePackSetService;
    }

    @GetMapping
    public String getKPacs(Model model) throws JsonProcessingException {
        List<KnowledgePackDTO> KPacs = kpacConversionService.convertAll(knowledgePackService.getAll(), KnowledgePackDTO.class);
        model.addAttribute(KPAC_LIST_JSON, mapper.writeValueAsString(KPacs));
        return KPAC_PAGE;
    }

    //!! This endpoint has async alternative in KnowledgePackController
    @PostMapping("/add")
    public String addKPac(@Valid @ModelAttribute KnowledgePackDTO dto, BindingResult validationResult, Model model) {
        if(validationResult.hasErrors()) {
            model.addAttribute(VALIDATION_ERRORS, validationResult.getAllErrors());
            return REDIRECT_KPAC_PAGE;
        }
        knowledgePackService.create(kpacConversionService.convert(dto, KnowledgePackModel.class));
        return REDIRECT_KPAC_PAGE;
    }

    //!! This endpoint has async alternative in KnowledgePackController
    @PostMapping("/delete")
    public String deleteKPac(@Valid @RequestParam("id") Long id) {
        knowledgePackService.delete(
                kpacConversionService.convert(KnowledgePackDTO.builder().id(id).build(), KnowledgePackModel.class));
        return REDIRECT_KPAC_PAGE;
    }
}
