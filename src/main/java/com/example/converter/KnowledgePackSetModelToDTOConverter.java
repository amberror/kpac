package com.example.converter;

import com.example.dto.KnowledgePackDTO;
import com.example.dto.KnowledgePackSetDTO;
import com.example.model.KnowledgePackModel;
import com.example.model.KnowledgePackSetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class KnowledgePackSetModelToDTOConverter
        implements Converter<KnowledgePackSetModel, KnowledgePackSetDTO> {

    private final Converter<KnowledgePackModel, KnowledgePackDTO> knowledgePackConverter;

    @Autowired
    public KnowledgePackSetModelToDTOConverter(Converter<KnowledgePackModel, KnowledgePackDTO> knowledgePackConverter) {
        this.knowledgePackConverter = knowledgePackConverter;
    }

    @Override
    public KnowledgePackSetDTO convert(KnowledgePackSetModel source) {
        return KnowledgePackSetDTO.builder()
                .id(source.getId())
                .title(source.getTitle())
                .kpacs(source.getKpacs().stream()
                        .map(knowledgePackConverter::convert)
                        .map(KnowledgePackDTO::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
