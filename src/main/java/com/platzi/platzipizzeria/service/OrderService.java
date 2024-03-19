package com.platzi.platzipizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platzi.platzipizzeria.persistence.entity.OrderEntity;
import com.platzi.platzipizzeria.persistence.projection.OrderSummary;
import com.platzi.platzipizzeria.persistence.repository.OrderRepository;
import com.platzi.platzipizzeria.service.dto.RandomOrderDto;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private static final String DELIVERY="D";
    private static final String CARRYOUT="C";
    private static final String ON_SITE="S";



    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();
        // aca se puede ver la informacion asi le haya colocado el fetch lazy
        orders.forEach(o -> System.out.println(o.getCustomer().getName()));
        return orders;
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders(){
        List<String> methods=Arrays.asList(DELIVERY,CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    //ESPECIFICO QUE SOLO LOS QUE TENGAN ROLE ADMIN A PARTIR DE LA SECURITY CONFIG PUEDAN INGRESAR A ESTE METODO 
    @Secured("ROLE_ADMIN")
    public List<OrderEntity> getCustomerOrders(String idCustomer){
        List<OrderEntity> customerOrders=this.orderRepository.findCustomerOrders(idCustomer);
        return customerOrders;
    }
    public OrderSummary getSummary(int orderId){
        return this.orderRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto){
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(),randomOrderDto.getMethod());
    }
}
