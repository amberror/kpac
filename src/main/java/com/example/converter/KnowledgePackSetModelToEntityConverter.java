package com.example.converter;

import com.example.dto.KnowledgePackDTO;
import com.example.entity.KnowledgePackEntity;
import com.example.entity.KnowledgePackSetEntity;
import com.example.model.KnowledgePackModel;
import com.example.model.KnowledgePackSetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class KnowledgePackSetModelToEntityConverter
        implements Converter<KnowledgePackSetModel, KnowledgePackSetEntity> {

    private final Converter<KnowledgePackModel, KnowledgePackEntity> knowledgePackConverter;

    @Autowired
    public KnowledgePackSetModelToEntityConverter(Converter<KnowledgePackModel, KnowledgePackEntity> knowledgePackConverter) {
        this.knowledgePackConverter = knowledgePackConverter;
    }

    @Override
    public KnowledgePackSetEntity convert(KnowledgePackSetModel source) {
        return KnowledgePackSetEntity.builder()
                .id(source.getId())
                .title(source.getTitle())
                .kpacs(source.getKpacs().stream()
                        .map(knowledgePackConverter::convert)
                        .collect(Collectors.toSet()))
                .build();
    }

}
