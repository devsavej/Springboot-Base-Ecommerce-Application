package com.savej.service;

import com.savej.model.OrderItem;
import com.savej.model.OrderItemDTO;
import com.savej.repository.OrderItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemDTOService {

    @Autowired
    private OrderItemDao oid;

    public OrderItemDTO getOrderitem(Long orderItemId){
        OrderItem orderItem=oid.findByorderItemId(orderItemId);

        if (orderItem==null){
            throw new RuntimeException("Items Not Found With Id:"+orderItemId);
        }else {
            OrderItemDTO orderItemDTO=new OrderItemDTO();

            orderItemDTO.setOrderItemId(orderItem.getOrderItemId());
            orderItemDTO.setPrice(orderItem.getPrice());
            orderItemDTO.setQuantity(orderItem.getQuantity());
            orderItemDTO.setOrderId(orderItem.getOrder().getOrderId());
            orderItemDTO.setProductId(orderItem.getProduct().getProductId());
            orderItemDTO.setProductName(orderItem.getProduct().getProductName());

            return orderItemDTO;
        }

    }
}
