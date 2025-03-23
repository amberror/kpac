package com.example.dao;

import com.example.dao.impl.KnowledgePackDaoImpl;
import com.example.entity.KnowledgePackEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class KnowledgePackDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private RowMapper<KnowledgePackEntity> mapper;

    @InjectMocks
    private KnowledgePackDaoImpl knowledgePackDao;

    private List<KnowledgePackEntity> entityList;

    private static final String MOCK_TITLE_1 = "Test K-PAC 1";
    private static final String MOCK_DESCRIPTION_1 = "Sample Description 1";
    private static final Long MOCK_ID_1 = 1L;
    private static final String MOCK_TITLE_2 = "Test K-PAC 2";
    private static final String MOCK_DESCRIPTION_2 = "Sample Description 2";
    private static final Long MOCK_ID_2 = 2L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        KnowledgePackEntity entity1 = KnowledgePackEntity.builder()
                .id(MOCK_ID_1)
                .title(MOCK_TITLE_1)
                .description(MOCK_DESCRIPTION_1)
                .build();

        KnowledgePackEntity entity2 = KnowledgePackEntity.builder()
                .id(MOCK_ID_2)
                .title(MOCK_TITLE_2)
                .description(MOCK_DESCRIPTION_2)
                .build();

        entityList = Arrays.asList(entity1, entity2);
    }

    @Test
    void testGetAll() {
        //given
        when(jdbcTemplate.query(anyString(), eq(mapper))).thenReturn(entityList);

        //when
        List<KnowledgePackEntity> result = knowledgePackDao.getAll();

        //then
        assertNotNull(result);
        assertEquals(2, result.size());
        KnowledgePackEntity result1 = result.stream().filter(entity -> MOCK_ID_1.equals(entity.getId())).findFirst()
                    .orElseThrow(() -> new RuntimeException("No entry with id : " + MOCK_ID_1 + " exists in result"));
        KnowledgePackEntity result2 = result.stream().filter(entity -> MOCK_ID_2.equals(entity.getId())).findFirst()
                .orElseThrow(() -> new RuntimeException("No entry with id : " + MOCK_ID_2 + " exists in result"));
        assertEquals(MOCK_TITLE_1, result1.getTitle());
        assertEquals(MOCK_TITLE_2, result2.getTitle());
        assertEquals(MOCK_DESCRIPTION_1, result1.getDescription());
        assertEquals(MOCK_DESCRIPTION_2, result2.getDescription());

        verify(jdbcTemplate).query(anyString(), eq(mapper));
    }

    /*OTHER TESTS*/
}
