package com.example.service.impl;

import com.example.dao.KnowledgePackDao;
import com.example.dao.KnowledgePackSetDao;
import com.example.entity.KnowledgePackEntity;
import com.example.model.KnowledgePackModel;
import com.example.service.KnowledgePackService;
import com.example.utils.KPacConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class KnowledgePackServiceImpl implements KnowledgePackService {

    private final KnowledgePackDao knowledgePackDao;
    private final KnowledgePackSetDao knowledgePackSetDao;
    private final KPacConversionService kpacConversionService;
    private final SimpleDateFormat format;

    @Autowired
    public KnowledgePackServiceImpl(KnowledgePackDao knowledgePackDao,
                                    KnowledgePackSetDao knowledgePackSetDao,
                                    KPacConversionService kpacConversionService,
                                    SimpleDateFormat format) {
        this.knowledgePackDao = knowledgePackDao;
        this.kpacConversionService = kpacConversionService;
        this.format = format;
        this.knowledgePackSetDao = knowledgePackSetDao;
    }

    @Override
    public List<KnowledgePackModel> getAll() {
        return kpacConversionService.convertAll(knowledgePackDao.getAll(), KnowledgePackModel.class);
    }

    @Override
    public KnowledgePackModel create(KnowledgePackModel model) {
        model.setCreationDate(format.format(new Date()));
        return kpacConversionService.convert(
                knowledgePackDao.create(
                        kpacConversionService.convert(model, KnowledgePackEntity.class)),
                KnowledgePackModel.class);
    }

    @Override
    @Transactional
    public boolean delete(KnowledgePackModel model) {

        knowledgePackSetDao.removePackFromRelation(model.getId());

        if (!knowledgePackDao.delete(kpacConversionService.convert(model, KnowledgePackEntity.class))) {
            throw new RuntimeException("Failed to delete KnowledgePack id : " + model.getId());
        }

        return true;
    }
}
