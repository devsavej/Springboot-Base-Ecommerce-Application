package com.savej.service;

import com.savej.expections.UserException;
import com.savej.model.Order;
import com.savej.model.OrderDTO;
import com.savej.model.User;
import com.savej.repository.OrderDao;
import com.savej.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDao odao;

    @Autowired
    private UserDao udao;


    public Order getOrderDetails(Long orderId){
        return odao.findByOrderId(orderId);

    }

    public List<Order> getOrderByUser(int userId){
        List<Order> orders=odao.findByUserUserId(userId);

        if (orders.isEmpty()){
            throw new UserException("No Order Found With User:"+userId);
        }else {
            return orders;
        }
    }

    public Order updateOrderStatus(Long orderId, String status){
        Order order=odao.findByOrderId(orderId);
        if (order!=null){
            order.setStatus(status);
            odao.save(order);
            return order;
        }else {
            throw new RuntimeException("Order Not Found With Id:"+orderId);
        }
    }


}
