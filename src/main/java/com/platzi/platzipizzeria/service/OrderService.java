package com.platzi.platzipizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzi.platzipizzeria.persistence.entity.OrderEntity;
import com.platzi.platzipizzeria.persistence.repository.OrderRepository;

@Service
public class OrderService {
    private final  OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }

    
}
