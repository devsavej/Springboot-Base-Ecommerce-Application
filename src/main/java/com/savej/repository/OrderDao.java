package com.savej.repository;

import com.savej.model.Order;
import com.savej.model.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Long> {

   // public Order postOrder(Order order);

    public Order findByOrderId(Long orderId);


    public List<Order> findByUserUserId(int userId);
}
