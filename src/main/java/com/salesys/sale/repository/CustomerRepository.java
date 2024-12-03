package com.salesys.sale.repository;

import com.salesys.sale.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByIsDeletedIsFalse();
    List<Customer> findByIsDeletedIsTrue();
    @Query("SELECT c FROM Customer c WHERE c.customerId = :customerId AND c.isDeleted = false")
    Optional<Customer> findActiveCustomerById(@Param("customerId") Long customerId);
}
