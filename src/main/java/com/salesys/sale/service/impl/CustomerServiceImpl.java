package com.salesys.sale.service.impl;

import com.salesys.sale.mapper.DataMapper;
import com.salesys.sale.model.Customer;
import com.salesys.sale.model.Dto.CustomerDto;
import com.salesys.sale.model.request.CustomerRequest;
import com.salesys.sale.repository.CustomerRepository;
import com.salesys.sale.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DataMapper dataMapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return new ArrayList<>(customers
                .stream()
                .map(dataMapper::toCustomerDto)
                .toList());
    }

    @Override
    public List<CustomerDto> getActiveCustomers() {
        List<Customer> customers = customerRepository.findByIsDeletedIsFalse();
        return new ArrayList<>(customers
                .stream()
                .map(dataMapper::toCustomerDto)
                .toList());
    }


    @Override
    public Customer addNewCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(dataMapper.newCustomer(customerRequest));
    }

    @Override
    public Customer updateCustomer(Long customerId, CustomerRequest customerPatch) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerRepository.save(dataMapper.updateCustomer(customer,customerPatch));
    }


    @Override
    public void deleteCustomerById(Long customerId) {
        if (customerRepository.findById(customerId).isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(customerId);
    }
}
