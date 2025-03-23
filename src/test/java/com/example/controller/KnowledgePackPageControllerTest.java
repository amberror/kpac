package com.example.controller;

import com.example.controller.impl.KnowledgePackPageController;
import com.example.dto.KnowledgePackDTO;
import com.example.model.KnowledgePackModel;
import com.example.service.impl.KnowledgePackServiceImpl;
import com.example.utils.impl.KPacConversionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class KnowledgePackPageControllerTest {
    @Mock
    private KnowledgePackServiceImpl knowledgePackService;

    @Mock
    private KPacConversionServiceImpl kpacConversionService;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private KnowledgePackPageController knowledgePackPageController;
    @Mock
    private Model model;
    private MockMvc mockMvc;

    private List<KnowledgePackDTO> KPacList;

    private static final String MOCK_TITLE_1 = "Test K-PAC 1";
    private static final String MOCK_DESCRIPTION_1 = "Sample Description 1";
    private static final Long MOCK_ID_1 = 1L;
    private static final String MOCK_TITLE_2 = "Test K-PAC 2";
    private static final String MOCK_DESCRIPTION_2 = "Sample Description 2";
    private static final Long MOCK_ID_2 = 2L;
    private static final String MOCK_JSON = "[{\"id\":1,\"title\":\"KPac 1\"}, {\"id\":2,\"title\":\"KPac 2\"}]";


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        KnowledgePackDTO dto1 = KnowledgePackDTO.builder()
                .id(MOCK_ID_1)
                .title(MOCK_TITLE_1)
                .description(MOCK_DESCRIPTION_1)
                .build();

        KnowledgePackDTO dto2 = KnowledgePackDTO.builder()
                .id(MOCK_ID_2)
                .title(MOCK_TITLE_2)
                .description(MOCK_DESCRIPTION_2)
                .build();

        KPacList = List.of(dto1, dto2);
    }

    @Test
    void testGetKPacs() throws Exception {
        // given
        when(knowledgePackService.getAll()).thenReturn(List.of(KnowledgePackModel.builder().build(), KnowledgePackModel.builder().build()));
        when(kpacConversionService.convertAll(Mockito.anyList(), Mockito.eq(KnowledgePackDTO.class))).thenReturn(KPacList);
        when(mapper.writeValueAsString(KPacList)).thenReturn(MOCK_JSON);
        mockMvc = MockMvcBuilders.standaloneSetup(knowledgePackPageController).build();

        // when/then
        mockMvc.perform(MockMvcRequestBuilders.get("/kpacs"))
                .andExpect(view().name("kpacPage"))
                .andExpect(model().attribute("kpacListJson", MOCK_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*OTHER TESTS*/
}
