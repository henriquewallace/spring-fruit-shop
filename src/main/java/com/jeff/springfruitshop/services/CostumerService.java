package com.jeff.springfruitshop.services;

import com.jeff.springfruitshop.api.v1.model.CostumerDTO;
import com.jeff.springfruitshop.domain.Costumer;

import java.util.List;

public interface CostumerService {

    List<CostumerDTO> getAllCostumers();

    CostumerDTO getCostumerById(Long id);
}
