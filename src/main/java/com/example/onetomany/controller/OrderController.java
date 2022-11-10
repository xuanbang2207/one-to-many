package com.example.onetomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.onetomany.dto.OrderRequest;
import com.example.onetomany.dto.OrderResponse;
import com.example.onetomany.entity.Customer;
import com.example.onetomany.repository.CustomerRepository;
import com.example.onetomany.repository.ProductRepository;

@RestController
public class OrderController {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("placeOrder")
	public Customer placeOrder(@RequestBody OrderRequest request) {
		return customerRepository.save(request.getCustomer());
	}
	
	@GetMapping("/findAllorders")
	public List<Customer> findAllOrders(){
		return customerRepository.findAll();
	}
	
	@GetMapping("/getinfo")
	public List<OrderResponse> getInfo(){
		return customerRepository.getJoinInfomation();
	}

}
