package com.example.service.impl;

import com.example.dao.KnowledgePackSetDao;
import com.example.entity.KnowledgePackEntity;
import com.example.entity.KnowledgePackSetEntity;
import com.example.model.KnowledgePackModel;
import com.example.model.KnowledgePackSetModel;
import com.example.service.KnowledgePackSetService;
import com.example.utils.KPacConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class KnowledgePackSetServiceImpl implements KnowledgePackSetService {

    private final KnowledgePackSetDao knowledgePackSetDao;
    private final KPacConversionService kpacConversionService;

    @Autowired
    public KnowledgePackSetServiceImpl(KnowledgePackSetDao knowledgePackSetDao,
                                       KPacConversionService kpacConversionService) {
        this.knowledgePackSetDao = knowledgePackSetDao;
        this.kpacConversionService = kpacConversionService;
    }

    @Override
    public List<KnowledgePackSetModel> getAll() {
        return kpacConversionService.convertAll(knowledgePackSetDao.getAll(), KnowledgePackSetModel.class);
    }

    @Override
    @Transactional
    public KnowledgePackSetModel create(KnowledgePackSetModel model) {
        KnowledgePackSetEntity entity = knowledgePackSetDao.create(kpacConversionService.convert(model, KnowledgePackSetEntity.class));

        if (entity.getKpacs() != null) {
            for (KnowledgePackEntity kpac : entity.getKpacs()) {
                if (!knowledgePackSetDao.addPackToSet(entity.getId(), kpac.getId())) {
                    throw new RuntimeException("Failed to add K-PAC to set setId : " + entity.getId() + ", kpacId : " + kpac.getId());
                }
            }
        }

        return kpacConversionService.convert(entity, KnowledgePackSetModel.class);
    }

    @Override
    @Transactional
    public boolean delete(KnowledgePackSetModel model) {

        knowledgePackSetDao.removeSetFromRelation(model.getId());

        if (!knowledgePackSetDao.delete(kpacConversionService.convert(model, KnowledgePackSetEntity.class))) {
            throw new RuntimeException("Failed to delete K-PAC SET, id : " + model.getId());
        }

        return true;
    }

    @Override
    public List<KnowledgePackModel> getKPacsAttachedToSet(KnowledgePackSetModel model) {
        return kpacConversionService.convertAll(
                knowledgePackSetDao.getKPacsAttachedToSet(
                        kpacConversionService.convert(model, KnowledgePackSetEntity.class)),
                KnowledgePackModel.class);
    }
}
