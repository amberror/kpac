package com.example.converter;

import com.example.entity.KnowledgePackEntity;
import com.example.model.KnowledgePackModel;
import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.util.Optional;

public class KnowledgePackModelToEntityConverter
        implements Converter<KnowledgePackModel, KnowledgePackEntity> {

    @Override
    public KnowledgePackEntity convert(KnowledgePackModel source) {
        return KnowledgePackEntity.builder()
                .id(source.getId())
                .title(source.getTitle())
                .description(source.getDescription())
                .creationDate(source.getCreationDate())
                .build();
    }

}
