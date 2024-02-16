package com.platzi.platzipizzeria.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.platzi.platzipizzeria.persistence.entity.OrderEntity;

public interface OrderRepository extends ListCrudRepository<OrderEntity,Long> {
    
}
