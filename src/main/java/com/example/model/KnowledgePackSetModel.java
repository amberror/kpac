package com.example.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class KnowledgePackSetModel {
    private Long id;
    private String title;
    private List<KnowledgePackModel> kpacs;
}
