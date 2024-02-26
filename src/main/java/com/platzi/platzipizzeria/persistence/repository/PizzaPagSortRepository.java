package com.platzi.platzipizzeria.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.platzi.platzipizzeria.persistence.entity.PizzaEntity;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity,Long>{


    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
    
}
