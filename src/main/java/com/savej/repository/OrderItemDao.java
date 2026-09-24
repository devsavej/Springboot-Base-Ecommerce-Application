package com.savej.repository;

import com.savej.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem,Long> {

    public OrderItem findByorderItemId(Long orderItemId);
}
