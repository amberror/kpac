package com.example.converter;

import com.example.entity.KnowledgePackEntity;
import com.example.entity.KnowledgePackSetEntity;
import com.example.model.KnowledgePackModel;
import com.example.model.KnowledgePackSetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.List;


public class KnowledgePackSetEntityToModelConverter
        implements Converter<KnowledgePackSetEntity, KnowledgePackSetModel> {

    private final Converter<KnowledgePackEntity, KnowledgePackModel> knowledgePackConverter;

    @Autowired
    public KnowledgePackSetEntityToModelConverter(Converter<KnowledgePackEntity, KnowledgePackModel> knowledgePackConverter) {
        this.knowledgePackConverter = knowledgePackConverter;
    }

    @Override
    public KnowledgePackSetModel convert(KnowledgePackSetEntity source) {
        return KnowledgePackSetModel.builder()
                .id(source.getId())
                .title(source.getTitle())
                .kpacs(source.getKpacs() == null ? List.of() :
                        source.getKpacs().stream()
                        .map(knowledgePackConverter::convert)
                        .toList())
                .build();
    }
}

