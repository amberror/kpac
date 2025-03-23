package com.example.converter;

import com.example.dto.KnowledgePackDTO;
import com.example.model.KnowledgePackModel;
import org.springframework.core.convert.converter.Converter;


public class KnowledgePackDTOToModelConverter
        implements Converter<KnowledgePackDTO, KnowledgePackModel> {

    @Override
    public KnowledgePackModel convert(KnowledgePackDTO source) {
            return KnowledgePackModel.builder()
                    .id(source.getId())
                    .title(source.getTitle())
                    .description(source.getDescription())
                    .creationDate(source.getCreationDate())
                    .build();
    }

}
