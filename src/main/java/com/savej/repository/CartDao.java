package com.savej.repository;

import com.savej.model.Cart;
import com.savej.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart,Long> {

    public Cart findByCartId(Long cartId)throws RuntimeException;

    Cart findByUserUserId(Integer userId);
}
