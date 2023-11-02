package com.jeff.springfruitshop.controllers.v1;

import com.jeff.springfruitshop.api.v1.model.CostumerDTO;
import com.jeff.springfruitshop.api.v1.model.CostumersListDTO;
import com.jeff.springfruitshop.services.CostumerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/costumers")
public class CostumerController {

    private final CostumerService costumerService;

    public CostumerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }

    @GetMapping
    public ResponseEntity<CostumersListDTO> getAllCostumers() {
        return new ResponseEntity<CostumersListDTO>(
                new CostumersListDTO(costumerService.getAllCostumers()), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostumerDTO> getCostumerById(@PathVariable Long id) {
        return new ResponseEntity<CostumerDTO>(
                costumerService.getCostumerById(Long.valueOf(id)), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CostumerDTO> createNewCostumer(@RequestBody CostumerDTO costumerDTO) {
        return new ResponseEntity<CostumerDTO>(
                costumerService.createNewCostumer(costumerDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostumerDTO> updateCostumer(@PathVariable Long id,
                                                      @RequestBody CostumerDTO costumerDTO) {
        return new ResponseEntity<CostumerDTO>(
                costumerService.saveCostumerByDTO(id, costumerDTO),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CostumerDTO> patchCostumer(@PathVariable Long id,
                                                     @RequestBody CostumerDTO costumerDTO) {
        return new ResponseEntity<CostumerDTO>(
                costumerService.patchCostumer(id, costumerDTO),
                HttpStatus.OK
        );
    }
}
