package com.savej.service;

import com.savej.model.Order;
import com.savej.model.OrderDTO;
import com.savej.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDTOService {
    @Autowired
    private OrderDao od;

    public OrderDTO getOrderById(Long orderId){
        Order dto= od.findByOrderId(orderId);

        if (dto==null){
            throw new RuntimeException("Order not found with id:"+orderId);
        }else {
            OrderDTO dto1=new OrderDTO();
            dto1.setOrderId(dto.getOrderId());
            dto1.setOrderDate(dto.getOrderDate());
            dto1.setStatus(dto.getStatus());
            dto1.setTotalAmount(dto.getTotalAmount());
            dto1.setUserId(dto.getUser().getUserId());
            return dto1;
        }
    }
}
