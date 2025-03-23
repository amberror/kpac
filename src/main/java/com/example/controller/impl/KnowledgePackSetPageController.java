package com.example.controller.impl;

import com.example.controller.AbstractKnowledgeSetPackController;
import com.example.dto.KnowledgePackDTO;
import com.example.dto.KnowledgePackSetDTO;
import com.example.model.KnowledgePackSetModel;
import com.example.service.KnowledgePackSetService;
import com.example.utils.KPacConversionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/set")
public class KnowledgePackSetPageController extends AbstractKnowledgeSetPackController {

    //Pages
    public static final String KPAC_ATTACHED_PAGE = "kpacAttachedPage";

    //Internal constants
    private static final String KPAC_LIST_JSON = "kpacListJson";
    private static final String KPAC_SET_ID = "kpacSetId";

    @Autowired
    public KnowledgePackSetPageController(KnowledgePackSetService knowledgePackSetService,
                                          KPacConversionService kpacConversionService,
                                          ObjectMapper mapper) {
        super(knowledgePackSetService, kpacConversionService, mapper);
    }

    @GetMapping("/{id}")
    public String getKPacSetAttachedPage(@PathVariable("id") Long id, Model model) throws JsonProcessingException {
        List<KnowledgePackDTO> attachedDto = kpacConversionService.convertAll(
                knowledgePackSetService.getKPacsAttachedToSet(
                        kpacConversionService.convert(KnowledgePackSetDTO.builder().id(id).kpacs(Set.of()).build(),
                                KnowledgePackSetModel.class)),
                KnowledgePackDTO.class);
        model.addAttribute(KPAC_LIST_JSON, mapper.writeValueAsString(attachedDto));
        model.addAttribute(KPAC_SET_ID, id);
        return KPAC_ATTACHED_PAGE;
    }

}
