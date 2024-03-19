package com.platzi.platzipizzeria.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.platzipizzeria.persistence.entity.CustomerEntity;
import com.platzi.platzipizzeria.persistence.entity.OrderEntity;
import com.platzi.platzipizzeria.service.CustomerService;
import com.platzi.platzipizzeria.service.OrderService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }


    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone){
        return ResponseEntity.ok(this.customerService.findByPhone(phone));

    }

     @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable("id") String idCustomer){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(idCustomer));
    }

    
}
