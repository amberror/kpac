package com.example.converter;

import com.example.dto.KnowledgePackDTO;
import com.example.dto.KnowledgePackSetDTO;
import com.example.model.KnowledgePackModel;
import com.example.model.KnowledgePackSetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class KnowledgePackSetDTOToModelConverter implements Converter<KnowledgePackSetDTO, KnowledgePackSetModel> {

    private final Converter<KnowledgePackDTO, KnowledgePackModel> knowledgePackConverter;

    @Autowired
    public KnowledgePackSetDTOToModelConverter(Converter<KnowledgePackDTO, KnowledgePackModel> knowledgePackConverter) {
        this.knowledgePackConverter = knowledgePackConverter;
    }

    @Override
    public KnowledgePackSetModel convert(KnowledgePackSetDTO source) {
        return KnowledgePackSetModel.builder()
                .id(source.getId())
                .title(source.getTitle())
                .kpacs(source.getKpacs().stream()
                        .map(id -> KnowledgePackDTO.builder().id(id).build())
                        .map(knowledgePackConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}
