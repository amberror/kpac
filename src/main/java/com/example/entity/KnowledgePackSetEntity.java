package com.example.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class KnowledgePackSetEntity {
    private Long id;
    private String title;
    private Set<KnowledgePackEntity> kpacs;
}
