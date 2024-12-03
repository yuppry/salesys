package com.salesys.sale.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column
    private String productName;

    @Column
    private String category;

    @Column
    private Double price;

    @Column
    private Integer quantity;

    @Column
    private Timestamp createdDate;

    @Column
    private Timestamp modifiedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetail> saleDetails;

}
