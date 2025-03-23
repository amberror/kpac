package com.example.converter;

import com.example.dto.KnowledgePackDTO;
import com.example.model.KnowledgePackModel;
import org.springframework.core.convert.converter.Converter;


public class KnowledgePackModelToDTOConverter
        implements Converter<KnowledgePackModel, KnowledgePackDTO> {


    @Override
    public KnowledgePackDTO convert(KnowledgePackModel source) {
        return KnowledgePackDTO.builder()
                .id(source.getId())
                .title(source.getTitle())
                .description(source.getDescription())
                .creationDate(source.getCreationDate())
                .build();
    }

}
