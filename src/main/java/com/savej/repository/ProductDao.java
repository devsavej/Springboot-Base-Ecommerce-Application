package com.savej.repository;

import com.savej.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductDao extends JpaRepository<Product,Long> {

    public List<Product> findByCategoryCategoryId(Long categoryid);

    public Product findByProductId(Long productId);
}
