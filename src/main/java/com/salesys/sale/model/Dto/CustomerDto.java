package com.salesys.sale.model.Dto;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CustomerDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private boolean isDeleted;
}
