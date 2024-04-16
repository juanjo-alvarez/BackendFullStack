package com.campusdual.model.dao;

import com.campusdual.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {
    List<Product> findByNameLike(String name);

    List<Product> findByStockIs(Integer stock);
}
