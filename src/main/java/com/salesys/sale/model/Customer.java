package com.salesys.sale.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private Date createdDate;

    @Column
    private Date modifiledDate;

    @Column
    private boolean isDeleted;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales;

}
