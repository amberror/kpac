package com.example.dao;

import com.example.entity.KnowledgePackEntity;
import com.example.entity.KnowledgePackSetEntity;

import java.util.List;

public interface KnowledgePackSetDao extends RestDao<KnowledgePackSetEntity, Long> {
    boolean addPackToSet(Long setId, Long packId);
    boolean removePackFromSet(Long setId, Long packId);
    boolean removePackFromRelation(Long packId);
    boolean removeSetFromRelation(Long setId);
    List<KnowledgePackEntity> getKPacsAttachedToSet(KnowledgePackSetEntity entity);
}
