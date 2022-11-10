package com.example.onetomany.controller;

import com.example.onetomany.entity.Customer;
import com.example.onetomany.model.CustomerRequest;
import com.example.onetomany.model.CustomerResponse;
import com.example.onetomany.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer addNewCustomer(@RequestBody CustomerRequest customerRequest){
        Customer response = customerService.addNewCustomer(customerRequest);
        return  response;
    }
    @GetMapping
    public Page<Customer> getAll (Pageable pageable){
        Page<Customer> page = customerService.getAll(pageable);
        return  page;
    }
    @PutMapping("/{id}")
    public Customer update (@PathVariable Long id, @RequestBody CustomerRequest customerRequest){
        Customer customer = customerService.update(id, customerRequest);
        return  customer;
    }
}
