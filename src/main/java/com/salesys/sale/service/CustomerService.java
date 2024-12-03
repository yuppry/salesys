package com.salesys.sale.service;

import com.salesys.sale.model.Customer;
import com.salesys.sale.model.Dto.CustomerDto;
import com.salesys.sale.model.request.CustomerRequest;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();
    List<CustomerDto> getActiveCustomers();
    Customer addNewCustomer(CustomerRequest customerRequest);
    Customer updateCustomer(Long customerId, CustomerRequest customerPatch);
    void deleteCustomerById(Long customerId);
}
