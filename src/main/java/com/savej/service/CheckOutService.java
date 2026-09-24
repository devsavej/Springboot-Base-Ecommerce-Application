package com.savej.service;

import com.savej.model.*;
import com.savej.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckOutService {

    @Autowired
    private CartDao cDao;

    @Autowired
    private CartItemsDao ciDao;

    @Autowired
    private UserDao uDao;

    @Autowired
    private ProductDao pDao;

    @Autowired
    private OrderItemDao oidao;

    @Autowired
    private OrderDao odao;

    @Transactional
    public CheckOutDTO checkOut(Long cartId) {

        Cart cart = cDao.findByCartId(cartId);

        if (cart == null) {
            throw new RuntimeException("Cart not found with ID: " + cartId);
        }

        List<CartItems> cartItems = ciDao.findByCartCartId(cartId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is Empty");
        }

        double total = 0;

        for (CartItems cartItems1 : cartItems) {

            double price = cartItems1.getProduct().getPrice();
            int quantity = cartItems1.getQuantity();

            total = total + (price * quantity);
        }

        Order order = new Order();

        order.setUser(cart.getUser());
        order.setStatus("PLACED");
        order.setOrderDate("2026/05/29");
        order.setTotalAmount(total);

        Order saveOrder = odao.save(order);

        List<OrderItemDTO> itemDTOList=new ArrayList<>();

        for (CartItems cartItems1 : cartItems) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(saveOrder);
            orderItem.setQuantity(cartItems1.getQuantity());
            orderItem.setProduct(cartItems1.getProduct());
            orderItem.setPrice(cartItems1.getProduct().getPrice());

            OrderItem savedItem = oidao.save(orderItem);
            OrderItemDTO dto = new OrderItemDTO();

            dto.setOrderItemId(savedItem.getOrderItemId());
            dto.setQuantity(savedItem.getQuantity());
            dto.setPrice(savedItem.getPrice());
            dto.setOrderId(saveOrder.getOrderId());
            dto.setProductId(savedItem.getProduct().getProductId());
            dto.setProductName(savedItem.getProduct().getProductName());

            itemDTOList.add(dto);
        }



        ciDao.deleteAll(cartItems);

        CheckOutDTO checkoutDTO = new CheckOutDTO();

        checkoutDTO.setOrderId(saveOrder.getOrderId());
        checkoutDTO.setOrderDate(saveOrder.getOrderDate());
        checkoutDTO.setStatus(saveOrder.getStatus());
        checkoutDTO.setTotalAmount(saveOrder.getTotalAmount());
        checkoutDTO.setOrderitems(itemDTOList);

        return checkoutDTO;
    }
    }

