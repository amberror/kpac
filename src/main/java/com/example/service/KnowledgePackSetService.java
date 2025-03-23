package com.example.service;

import com.example.model.KnowledgePackModel;
import com.example.model.KnowledgePackSetModel;

import java.util.List;

public interface KnowledgePackSetService extends RestService<KnowledgePackSetModel> {
    List<KnowledgePackModel> getKPacsAttachedToSet(KnowledgePackSetModel model);
}
