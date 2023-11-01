package com.jeff.springfruitshop.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CostumersListDTO {

    List<CostumerDTO> costumers;
}
