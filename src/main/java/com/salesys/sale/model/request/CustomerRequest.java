package com.salesys.sale.model.request;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
