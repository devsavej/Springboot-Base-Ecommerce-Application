package com.savej.repository;

import com.savej.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemsDao extends JpaRepository<CartItems,Long> {

     List<CartItems> findByCartCartId(Long cartId);




}
