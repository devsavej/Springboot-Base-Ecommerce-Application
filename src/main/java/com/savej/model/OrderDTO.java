package com.savej.model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {


    private Long orderId;
    private String orderDate;
    private String status;
    private double totalAmount;


    private Integer userId;
}
