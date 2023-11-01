package com.jeff.springfruitshop.api.v1.mapper;

import com.jeff.springfruitshop.api.v1.model.CostumerDTO;
import com.jeff.springfruitshop.domain.Costumer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CostumerMapper {

    CostumerMapper INSTANCE = Mappers.getMapper(CostumerMapper.class);

    CostumerDTO costumerToCostumerDTO(Costumer costumer);
}
