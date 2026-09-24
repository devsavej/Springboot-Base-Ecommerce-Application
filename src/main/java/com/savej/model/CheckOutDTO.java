package com.savej.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutDTO {

    private Long orderId;
    private String orderDate;
    private String status;
    private double totalAmount;
    List<OrderItemDTO> orderitems;
}
