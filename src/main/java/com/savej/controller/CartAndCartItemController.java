package com.savej.controller;

import com.savej.expections.UserException;
import com.savej.model.*;
import com.savej.repository.*;
import com.savej.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CartAndCartItemController {

    @Autowired
    private CartDao cDao;

    @Autowired
    private CartItemsDao ciDao;

    @Autowired
    private UserDao uDao;

    @Autowired
    private ProductDao pDao;

    @Autowired
    private OrderDao odao;

    @Autowired
    private CheckOutService cosService;

//    @Autowired
//    private OrderItemDao oidao;





    @PostMapping("postUser/{userId}")
    public Cart postCart(@PathVariable("userId") Integer userId){

        User user=uDao.findByUserId(userId);

        Cart cart=new Cart();
        if (user!=null){
            cart.setUser(user);
            cDao.save(cart);
            return cart;
        }else {
            throw new UserException("User Not Found With Id:"+userId);
        }
    }
//
//    @PostMapping("postItem/{cartId}/{productId}")
//    public CartItems postCartItems(@RequestBody CartItems cartItems,
//                                   @PathVariable("cartId") Long cartId,
//                                   @PathVariable("productId") Long productId){
//     Cart cart= cDao.findByCartId(cartId);
//     if (cart!=null){
//         cartItems.setCart(cart);
//     }else {
//         throw new RuntimeException("Cart Not Found With id:"+cart);
//     }
//
//     Product product=pDao.findByProductId(productId);
//
//     if (product!=null){
//         cartItems.setProduct(product);
//     }else {
//         throw new RuntimeException("Product Not Found With Id:"+product);
//     }
//
//     return ciDao.save(cartItems);
//
//    }

    @GetMapping("/findByCartId/{cartId}")
    public List<CartItems> findCartItemByCardHandler(@PathVariable Long cartId) {

        List<CartItems> cartItems = ciDao.findByCartCartId(cartId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Items Not Found With Cart: " + cartId);
        } else {
            return cartItems;
        }
    }

    @GetMapping("findCartByUser/{userId}")
    public Cart findByCartIdByUserHandler(@PathVariable Integer userId){
        Cart cart=cDao.findByUserUserId(userId);

        if (cart!=null){
            return cart;
        }else {
            throw new RuntimeException("Cart Not Found With UserId:"+userId);
        }
    }

    @DeleteMapping("deleteItem/{cartItemsId}")
    public CartItems deleteCartItemHandler(@PathVariable Long cartItemsId){
       Optional<CartItems> opt= ciDao.findById(cartItemsId);


        if (opt.isPresent()) {
            CartItems cartItems1=opt.get();
            ciDao.deleteById(cartItemsId);
            return cartItems1;
        }else {
            throw new RuntimeException("CartItem Not Found With Id:"+cartItemsId);
        }
    }

    @PutMapping("/updateItem/{cartItemId}")
    public CartItems updateItemsHandler(@PathVariable Long cartItemId,
                                 @RequestParam Integer quantity){
        Optional<CartItems> opt=ciDao.findById(cartItemId);
        if (opt.isPresent()){
            CartItems cartItems=opt.get();
            cartItems.setQuantity(quantity);
            return ciDao.save(cartItems);
        }else {
            throw new RuntimeException("Item Not Found With Id:"+cartItemId);
        }
    }

    @GetMapping("cartTotal/{cartId}")
    public double getCartTotalHandler(@PathVariable Long cartId){

       List<CartItems> cartItems= ciDao.findByCartCartId(cartId);
       if (cartItems.isEmpty()){
           throw new RuntimeException("Items Not Found With Cart Id:"+cartId);
       }else {

           double total=0;

           for (CartItems cartItems1:cartItems){
               double price=cartItems1.getProduct().getPrice();
               int Quantity=cartItems1.getQuantity();

               total=total+(price*Quantity);

           }
           return total;
       }


    }

    @PostMapping("postItem/{cartId}/{productId}")
    public CartItems createCartItemsHandler(@RequestBody CartItems cartItems,
                                   @PathVariable("cartId") Long cartId,
                                   @PathVariable("productId") Long productId){
        Cart cart=cDao.findByCartId(cartId);
        if (cart==null){
            throw new RuntimeException("Cart Not Found With Id:"+cartId);

        }

        Product product=pDao.findByProductId(productId);
        if (product==null){
            throw new RuntimeException("Product Not Found With Id:"+productId);
        }

        if (cartItems.getQuantity()>product.getQuantity()){
            throw new RuntimeException("Insufficient Available Stock Is:"+product.getQuantity());
        }

        cartItems.setCart(cart);
        cartItems.setProduct(product);

        product.setQuantity(product.getQuantity()-cartItems.getQuantity());

        pDao.save(product);

        return ciDao.save(cartItems);
    }

    @PostMapping("checkOut/{cartId}")
    public CheckOutDTO createOrderHandler(@PathVariable Long cartId) {
        return cosService.checkOut(cartId);
    }
}
