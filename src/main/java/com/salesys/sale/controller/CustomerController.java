package com.salesys.sale.controller;

import com.salesys.sale.model.Customer;
import com.salesys.sale.model.Dto.CustomerDto;
import com.salesys.sale.model.request.CustomerRequest;
import com.salesys.sale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController extends BaseResponse {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(){
        List<CustomerDto> data = customerService.getAllCustomers();
        return buildResponse(data);
    }

    @GetMapping()
    public ResponseEntity<?> getAllActiveCustomers(){
        List<CustomerDto> data = customerService.getActiveCustomers();
        return buildResponse(data);
    }

    @PostMapping()
    public ResponseEntity<?> addNewCustomer(@RequestBody CustomerRequest customerRequest){
        Customer data = customerService.addNewCustomer(customerRequest);
        return buildResponse(data);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequest customerPatch){
        Customer data = customerService.updateCustomer(customerId,customerPatch);
        return buildResponse(data);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomerById(@RequestParam Long customerId){
        customerService.deleteCustomerById(customerId);
        return buildResponse("CustomerId " + customerId + " is deleted");
    }
}
