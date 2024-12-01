package com.salesys.sale.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

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
    private int quantity;

    @Column
    private Date createdDate;

    @Column
    private Date modifiledDate;

    @Column
    private boolean isDeleted;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

}
