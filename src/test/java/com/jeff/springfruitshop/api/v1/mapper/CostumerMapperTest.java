package com.jeff.springfruitshop.api.v1.mapper;

import com.jeff.springfruitshop.api.v1.model.CostumerDTO;
import com.jeff.springfruitshop.domain.Costumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CostumerMapperTest {

    public static final Long ID = 1L;
    public static final String FIRSTNAME = "Walter";
    public static final String LASTNAME = "White";

    CostumerMapper costumerMapper = CostumerMapper.INSTANCE;

    @Test
    void costumerToCostumerDTO() {
        Costumer costumer = new Costumer();
        costumer.setId(ID);
        costumer.setFirstName(FIRSTNAME);
        costumer.setLastName(LASTNAME);

        CostumerDTO costumerDTO = costumerMapper.costumerToCostumerDTO(costumer);

        assertEquals(Long.valueOf(ID), costumerDTO.getId());
        assertEquals(FIRSTNAME, costumerDTO.getFirstName());
        assertEquals(LASTNAME, costumerDTO.getLastName());
    }

    @Test
    void costumerDtoToCostumer() {
        CostumerDTO costumerDTO = new CostumerDTO();
        costumerDTO.setFirstName(FIRSTNAME);
        costumerDTO.setLastName(LASTNAME);

        Costumer costumer = costumerMapper.costumerDtoToCostumer(costumerDTO);

        assertEquals(FIRSTNAME, costumer.getFirstName());
        assertEquals(LASTNAME, costumer.getLastName());
    }
}