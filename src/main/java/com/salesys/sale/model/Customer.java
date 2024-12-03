package com.salesys.sale.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
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
    private Timestamp createdDate;

    @Column
    private Timestamp modifiedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales;

}
