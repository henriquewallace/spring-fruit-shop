package com.jeff.springfruitshop.bootstrap;

import com.jeff.springfruitshop.domain.Category;
import com.jeff.springfruitshop.domain.Costumer;
import com.jeff.springfruitshop.repositories.CategoryRepository;
import com.jeff.springfruitshop.repositories.CostumerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CostumerRepository costumerRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     CostumerRepository costumerRepository) {
        this.categoryRepository = categoryRepository;
        this.costumerRepository = costumerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        fruits.setName("Dried");

        Category fresh = new Category();
        fruits.setName("Fresh");

        Category exotic = new Category();
        fruits.setName("Exotic");

        Category nuts = new Category();
        fruits.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded = " + categoryRepository.count());

        Costumer costumer1 = new Costumer();
        costumer1.setFirstName("Vin");
        costumer1.setLastName("Diesel");

        Costumer costumer2 = new Costumer();
        costumer2.setFirstName("Paul");
        costumer2.setLastName("Walker");

        Costumer costumer3 = new Costumer();
        costumer3.setFirstName("Jessie");
        costumer3.setLastName("Pinkman");

        costumerRepository.save(costumer1);
        costumerRepository.save(costumer2);
        costumerRepository.save(costumer3);

        System.out.println("Costumers loaded = " + categoryRepository.count());
    }
}
