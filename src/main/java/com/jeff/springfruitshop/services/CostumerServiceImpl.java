package com.jeff.springfruitshop.services;

import com.jeff.springfruitshop.api.v1.mapper.CostumerMapper;
import com.jeff.springfruitshop.api.v1.model.CostumerDTO;
import com.jeff.springfruitshop.domain.Costumer;
import com.jeff.springfruitshop.repositories.CostumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostumerServiceImpl implements CostumerService {

    private final CostumerRepository costumerRepository;
    private final CostumerMapper costumerMapper;

    public CostumerServiceImpl(CostumerRepository costumerRepository, CostumerMapper costumerMapper) {
        this.costumerRepository = costumerRepository;
        this.costumerMapper = costumerMapper;
    }

    @Override
    public List<CostumerDTO> getAllCostumers() {
        return costumerRepository.findAll()
                .stream()
                .map(costumerMapper::costumerToCostumerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CostumerDTO getCostumerById(Long id) {
        return costumerMapper.costumerToCostumerDTO(costumerRepository.getCostumerById(id));
    }

    @Override
    public CostumerDTO createNewCostumer(CostumerDTO costumerDTO) {
        return saveAndReturnDTO(costumerMapper.costumerDtoToCostumer(costumerDTO));
    }

    @Override
    public CostumerDTO saveCostumerByDTO(Long id, CostumerDTO costumerDTO) {
        Costumer costumer = costumerMapper.costumerDtoToCostumer(costumerDTO);
        costumer.setId(id);

        return saveAndReturnDTO(costumer);
    }

    @Override
    public CostumerDTO patchCostumer(Long id, CostumerDTO costumerDTO) {
        return costumerRepository.findById(id).map(costumer -> {
            if (costumer.getFirstName() != null) {
                costumer.setFirstName(costumerDTO.getFirstName());
            }

            if (costumer.getLastName() != null) {
                costumer.setLastName(costumerDTO.getLastName());
            }

            return costumerMapper.costumerToCostumerDTO(costumerRepository.save(costumer));
        }).orElseThrow(RuntimeException::new);
    }

    private CostumerDTO saveAndReturnDTO(Costumer costumer) {
        Costumer savedCostumer = costumerRepository.save(costumer);

        return costumerMapper.costumerToCostumerDTO(costumer);
    }
}
