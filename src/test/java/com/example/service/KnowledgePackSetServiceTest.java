package com.example.service;

import com.example.dao.KnowledgePackSetDao;
import com.example.entity.KnowledgePackEntity;
import com.example.entity.KnowledgePackSetEntity;
import com.example.model.KnowledgePackModel;
import com.example.model.KnowledgePackSetModel;
import com.example.service.impl.KnowledgePackSetServiceImpl;
import com.example.utils.KPacConversionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class KnowledgePackSetServiceTest {

    @Mock
    private KnowledgePackSetDao knowledgePackSetDao;
    @Mock
    private KPacConversionService kpacConversionService;

    @InjectMocks
    private KnowledgePackSetServiceImpl knowledgePackSetService;

    private KnowledgePackSetModel KPacSetModelMock;
    private KnowledgePackSetEntity KPacSetEntityMock;
    private List<KnowledgePackModel> kpacModelList;
    private Set<KnowledgePackEntity> kpacEntityList;

    private static final String MOCK_TITLE = "Test K-PAC";
    private static final Long MOCK_ID = 1L;
    private static final String ENTITY_EXCEPTION_MESSAGE = "Test EntityException";

    @BeforeEach
    void setUp() {

        kpacModelList = List.of(
                KnowledgePackModel.builder().id(1L).build(),
                KnowledgePackModel.builder().id(2L).build(),
                KnowledgePackModel.builder().id(3L).build()
        );

        kpacEntityList = Set.of(
            KnowledgePackEntity.builder().id(1L).build(),
            KnowledgePackEntity.builder().id(2L).build(),
            KnowledgePackEntity.builder().id(3L).build()
        );

        KPacSetModelMock = KnowledgePackSetModel.builder()
                .id(MOCK_ID)
                .title(MOCK_TITLE)
                .kpacs(kpacModelList)
                .build();

        KPacSetEntityMock = KnowledgePackSetEntity.builder()
                .id(MOCK_ID)
                .title(MOCK_TITLE)
                .kpacs(kpacEntityList)
                .build();
    }

    @Test
    void testCreateKPacSet_Success() {
        //given
        KnowledgePackSetModel knowledgePackSetModelWithoutId = KnowledgePackSetModel.builder()
                .title(MOCK_TITLE)
                .kpacs(kpacModelList)
                .build();
        KnowledgePackSetEntity knowledgePackSetEntityWithoutId = KnowledgePackSetEntity.builder()
                .title(MOCK_TITLE)
                .kpacs(kpacEntityList)
                .build();

        when(kpacConversionService.convert(eq(knowledgePackSetModelWithoutId), eq(KnowledgePackSetEntity.class))).thenReturn(knowledgePackSetEntityWithoutId);
        when(knowledgePackSetDao.create(eq(knowledgePackSetEntityWithoutId))).thenReturn(KPacSetEntityMock);
        when(kpacConversionService.convert(eq(KPacSetEntityMock), eq(KnowledgePackSetModel.class))).thenReturn(KPacSetModelMock);

        //when
        KnowledgePackSetModel result = knowledgePackSetService.create(knowledgePackSetModelWithoutId);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(MOCK_ID, result.getId());
        Assertions.assertEquals(MOCK_TITLE, result.getTitle());
        Assertions.assertNotNull(result.getKpacs());
        Assertions.assertEquals(kpacEntityList.size(), result.getKpacs().size());

        verify(knowledgePackSetDao, times(1)).create(knowledgePackSetEntityWithoutId);
    }

    /*OTHER TESTS*/

}
