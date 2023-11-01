package com.jeff.springfruitshop.repositories;

import com.jeff.springfruitshop.domain.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {

    Costumer getCostumerById(Long id);
}
