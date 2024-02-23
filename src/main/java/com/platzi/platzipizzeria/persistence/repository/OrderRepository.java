package com.platzi.platzipizzeria.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.platzi.platzipizzeria.persistence.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity,Long> {
    
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);
}
