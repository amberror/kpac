package com.example.converter;

import com.example.entity.KnowledgePackEntity;
import com.example.model.KnowledgePackModel;
import org.springframework.core.convert.converter.Converter;


public class KnowledgePackEntityToModelConverter
        implements Converter<KnowledgePackEntity, KnowledgePackModel> {

    @Override
    public KnowledgePackModel convert(KnowledgePackEntity source) {
        return KnowledgePackModel.builder()
                .id(source.getId())
                .title(source.getTitle())
                .description(source.getDescription())
                .creationDate(source.getCreationDate())
                .build();
    }

}
