package com.example.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class KnowledgePackEntity {
    private Long id;
    private String title;
    private String description;
    private String creationDate;
}
