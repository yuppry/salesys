package com.salesys.sale.repository;

import com.salesys.sale.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIsDeletedIsFalse();
    List<Product> findByIsDeletedIsTrue();

}
