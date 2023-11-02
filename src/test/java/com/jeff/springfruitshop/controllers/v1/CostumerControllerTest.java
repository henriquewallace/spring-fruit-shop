package com.jeff.springfruitshop.controllers.v1;

import com.jeff.springfruitshop.api.v1.model.CostumerDTO;
import com.jeff.springfruitshop.services.CostumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.jeff.springfruitshop.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CostumerControllerTest {

    public static final String FIRSTNAME = "Walter";
    public static final String LASTNAME = "White";

    @Mock
    CostumerService costumerService;

    @InjectMocks
    CostumerController costumerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(costumerController).build();
    }

    @Test
    void getAllCostumers() throws Exception {
        CostumerDTO costumer1 = new CostumerDTO();
        costumer1.setId(1L);
        costumer1.setFirstName(FIRSTNAME);
        costumer1.setLastName(LASTNAME);

        CostumerDTO costumer2 = new CostumerDTO();
        costumer2.setId(2L);
        costumer2.setFirstName("Nathan");
        costumer2.setLastName("Drake");

        List<CostumerDTO> costumers = Arrays.asList(costumer1, costumer2);

        when(costumerService.getAllCostumers()).thenReturn(costumers);

        mockMvc.perform(get("/api/v1/costumers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.costumers", hasSize(2)));
    }

    @Test
    void getCostumerById() throws Exception {
        CostumerDTO costumerDTO = new CostumerDTO();
        costumerDTO.setId(1L);
        costumerDTO.setFirstName("Elena");
        costumerDTO.setLastName("Parker");

        when(costumerService.getCostumerById(anyLong())).thenReturn(costumerDTO);

        mockMvc.perform(get("/api/v1/costumers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Elena")));
    }

    @Test
    void createNewCostumer() throws Exception {
        CostumerDTO costumerDTO = new CostumerDTO();
        costumerDTO.setFirstName("Elena");
        costumerDTO.setLastName("Parker");

        CostumerDTO returnDto = new CostumerDTO();
        returnDto.setFirstName(costumerDTO.getFirstName());
        returnDto.setLastName(costumerDTO.getLastName());

        when(costumerService.createNewCostumer(costumerDTO)).thenReturn(returnDto);

        mockMvc.perform(post("/api/v1/costumers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(costumerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Elena")));
    }
}