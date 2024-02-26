package com.platzi.platzipizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {

    // select po.
    // id_order as idOrder,cu.name as customerName,
    // po.date as orderDate,
    // po.total as orderTotal,STRING_AGG(PI.name, ', ') AS pizzaNames from pizza_order po join customer cu using(id_customer) join order_item

    // oi using (id_order) join pizza

    // pi using(id_pizza) where po.id_order=1 GROUP BY po.id_order, cu.id_customer, po.date, po.total;

    Integer getIdOrder();

    String getCustomerName();

    LocalDateTime getOrderDate();

    Double getOrderTotal();

    String getPizzaNames();

}
