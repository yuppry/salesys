package com.salesys.sale.repository;

import com.salesys.sale.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE DATE(s.saleDate) = :date AND s.isDeleted = false")
    List<Sale> findBySaleDate(@Param("date")LocalDate date);
}
