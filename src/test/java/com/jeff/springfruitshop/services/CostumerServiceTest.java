package com.jeff.springfruitshop.services;

import com.jeff.springfruitshop.api.v1.mapper.CostumerMapper;
import com.jeff.springfruitshop.api.v1.model.CostumerDTO;
import com.jeff.springfruitshop.domain.Costumer;
import com.jeff.springfruitshop.repositories.CostumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CostumerServiceTest {

    public static final Long ID = 1L;
    public static final String FIRSTNAME = "Walter";
    public static final String LASTNAME = "White";

    CostumerService costumerService;

    @Mock
    CostumerRepository costumerRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        costumerService = new CostumerServiceImpl(costumerRepository, CostumerMapper.INSTANCE);
    }

    @Test
    void getAllCostumers() {
        List<Costumer> costumers = Arrays.asList(new Costumer(), new Costumer(), new Costumer());

        when(costumerRepository.findAll()).thenReturn(costumers);

        List<CostumerDTO> costumerDTOS = costumerService.getAllCostumers();

        assertEquals(3, costumerDTOS.size());
    }

    @Test
    void getCostumerById() {
        Costumer costumer = new Costumer();
        costumer.setId(ID);
        costumer.setFirstName(FIRSTNAME);
        costumer.setLastName(LASTNAME);

        when(costumerRepository.getCostumerById(anyLong())).thenReturn(costumer);

        CostumerDTO costumerDTO = costumerService.getCostumerById(ID);

        assertEquals(ID, costumerDTO.getId());
        assertEquals(FIRSTNAME, costumerDTO.getFirstName());
        assertEquals(LASTNAME, costumerDTO.getLastName());
    }

    @Test
    void createNewCostumer() {
        CostumerDTO costumerDTO = new CostumerDTO();
        costumerDTO.setFirstName(FIRSTNAME);
        costumerDTO.setLastName(LASTNAME);

        Costumer savedCostumer = new Costumer();
        savedCostumer.setId(1L);
        savedCostumer.setFirstName(costumerDTO.getFirstName());
        savedCostumer.setLastName(costumerDTO.getLastName());

        when(costumerRepository.save(any(Costumer.class))).thenReturn(savedCostumer);

        CostumerDTO savedDTO = costumerService.createNewCostumer(costumerDTO);

        assertEquals(costumerDTO.getFirstName(), savedDTO.getFirstName());
    }
}