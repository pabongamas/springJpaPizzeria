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
        List<OrderEntity> orders=this.orderRepository.findAll();
        // aca se puede ver la informacion asi le haya colocado el fetch lazy 
        orders.forEach(o->System.out.println(o.getCustomer().getName()));
        return orders;
    }

    
}
