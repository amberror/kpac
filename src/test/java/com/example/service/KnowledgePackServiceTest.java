package com.example.service;

import com.example.dao.impl.KnowledgePackDaoImpl;
import com.example.dao.impl.KnowledgePackSetDaoImpl;
import com.example.entity.KnowledgePackEntity;
import com.example.exception.EntityException;
import com.example.model.KnowledgePackModel;
import com.example.service.impl.KnowledgePackServiceImpl;
import com.example.utils.impl.KPacConversionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KnowledgePackServiceTest {

    @Mock
    private KnowledgePackDaoImpl knowledgePackDao;
    @Mock
    private KnowledgePackSetDaoImpl knowledgePackSetDao;
    @Mock
    private KPacConversionServiceImpl kpacConversionService;

    @Mock
    private SimpleDateFormat format;

    @InjectMocks
    private KnowledgePackServiceImpl knowledgePackService;

    private KnowledgePackModel KPacModelMock;
    private KnowledgePackEntity KPacEntityMock;

    private static final String MOCK_TITLE = "Test K-PAC";
    private static final String MOCK_DESCRIPTION = "Sample Description";
    private static final Long MOCK_ID = 1L;
    private static final String MOCK_DATE = "01-01-2021";
    private static final String ENTITY_EXCEPTION_MESSAGE = "Test EntityException";

    @BeforeEach
    void setUp() {
        KPacModelMock = KnowledgePackModel.builder()
                .id(MOCK_ID)
                .title(MOCK_TITLE)
                .description(MOCK_DESCRIPTION)
                .build();

        KPacEntityMock = KnowledgePackEntity.builder()
                .id(MOCK_ID)
                .title(MOCK_TITLE)
                .description(MOCK_DESCRIPTION)
                .build();

        StringBuffer sb = new StringBuffer(MOCK_DATE);
        lenient().when(format.format(new Date(), new StringBuffer(), new FieldPosition(1))).thenReturn(sb);
    }

    @Test
    void testDeleteKPac_Success() {
        //given
        KnowledgePackModel KPacModelMockIdOnly = KnowledgePackModel.builder()
                .id(MOCK_ID)
                .build();

        when(knowledgePackSetDao.removePackFromRelation(MOCK_ID)).thenReturn(Boolean.FALSE);
        when(kpacConversionService.convert(eq(KPacModelMockIdOnly), eq(KnowledgePackEntity.class))).thenReturn(KPacEntityMock);
        when(knowledgePackDao.delete(eq(KPacEntityMock))).thenReturn(Boolean.TRUE);

        //when
        Boolean result = knowledgePackService.delete(KPacModelMockIdOnly);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result);

        verify(knowledgePackDao, times(1)).delete(KPacEntityMock);
        verify(knowledgePackSetDao, times(1)).removePackFromRelation(MOCK_ID);
    }

    @Test
    void testDeleteKPac_Failed() {
        //given
        KnowledgePackModel KPacModelMockIdOnly = KnowledgePackModel.builder()
                .id(MOCK_ID)
                .build();

        when(knowledgePackSetDao.removePackFromRelation(MOCK_ID)).thenReturn(Boolean.TRUE);
        when(kpacConversionService.convert(eq(KPacModelMockIdOnly), eq(KnowledgePackEntity.class))).thenReturn(KPacEntityMock);
        when(knowledgePackDao.delete(eq(KPacEntityMock))).thenThrow(new EntityException(ENTITY_EXCEPTION_MESSAGE));

        //then
        Assertions.assertThrows(EntityException.class, () -> knowledgePackService.delete(KPacModelMockIdOnly));

        verify(knowledgePackDao, times(1)).delete(any(KnowledgePackEntity.class));
        verify(knowledgePackSetDao, times(1)).removePackFromRelation(MOCK_ID);
    }

    /* OTHER TESTS */
}
