package com.platzi.platzipizzeria.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.platzi.platzipizzeria.persistence.entity.OrderEntity;
import com.platzi.platzipizzeria.persistence.projection.OrderSummary;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer=:id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

    @Query(value="select po.id_order as idOrder,cu.name as customerName,po.date as orderDate,po.total as orderTotal,STRING_AGG(PI.name, ', ') AS pizzaNames"+
    " from pizza_order po join customer cu using(id_customer) join order_item oi using (id_order) join pizza pi using(id_pizza) where po.id_order=:orderId "+
    " GROUP BY po.id_order, cu.id_customer, po.date, po.total",nativeQuery=true)
    OrderSummary findSummary(@Param("orderId")int orderId);

}
