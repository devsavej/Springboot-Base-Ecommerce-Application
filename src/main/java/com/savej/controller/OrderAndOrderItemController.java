package com.savej.controller;

import com.savej.expections.UserException;
import com.savej.model.*;
import com.savej.repository.OrderDao;
import com.savej.repository.OrderItemDao;
import com.savej.repository.ProductDao;
import com.savej.repository.UserDao;
import com.savej.service.OrderDTOService;
import com.savej.service.OrderItemDTOService;
import com.savej.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderAndOrderItemController {
    @Autowired
    private OrderDao oDao;

    @Autowired
    private UserDao uDao;

    @Autowired
    private ProductDao pDao;

    @Autowired
    private OrderItemDao oiDao;

    @Autowired
    private OrderService oService;

    @Autowired
    private OrderDTOService ods;

    @Autowired
    private OrderItemDTOService oids;


    @PostMapping("/order/{userId}")
    public Order createOrderHandler(@RequestBody Order order,
                             @PathVariable("userId") Integer userId){

        User user=uDao.findByUserId(userId);

        if (user!=null){
            order.setUser(user);
        }else throw new UserException("User Not Found With Id:"+userId);



        return oDao.save(order);

    }

    @PostMapping("orderItem/{orderId}/{productId}")
    public OrderItem postOrderItemHandler(@RequestBody OrderItem orderItem,
                                          @PathVariable("orderId") Long orderId,
                                          @PathVariable("productId") Long productId){
        Optional<Order> opt=oDao.findById(orderId);
        if (opt.isPresent()){
            Order order=opt.get();
            orderItem.setOrder(order);
        }else {
            throw new RuntimeException("Order Not Found With Id:"+orderId);
        }

        Optional<Product> opt1=pDao.findById(productId);
        if (opt1.isPresent()){
            Product product=opt1.get();
            orderItem.setProduct(product);
        }else {
            throw new RuntimeException("Product not found wiyh id:"+productId);
        }

        return oiDao.save(orderItem);



    }

    @GetMapping("/getorder/{orderId}")
    public Order GetOrderByIdHandler(@PathVariable Long orderId){
       Order order= oService.getOrderDetails(orderId);
       if (order!=null){
           return order;
       }else {
           throw new RuntimeException("Order Not Found With Id:"+orderId);
       }

    }

    @GetMapping("/getorderbyuser/{userId}")
    public List<Order> getOrderByUserHandler(@PathVariable int userId) {

        return oService.getOrderByUser(userId);

    }

    @PutMapping("updatestatus/{orderId}")
    public Order updateOrderStatusHandler(@PathVariable Long orderId,@RequestParam String status){

       return oService.updateOrderStatus(orderId,status);

    }

    @GetMapping("/orderbydto/{orderId}")
    public OrderDTO getOrderByIdHandler(@PathVariable Long orderId){

       return ods.getOrderById(orderId);

    }

    @GetMapping("getorderitems/{orderItemId}")
    public OrderItemDTO getOrderItemsByIdHandler(@PathVariable Long orderItemId){

        return oids.getOrderitem(orderItemId);


    }
}
