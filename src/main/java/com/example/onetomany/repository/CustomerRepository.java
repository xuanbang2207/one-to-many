package com.example.onetomany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.onetomany.dto.OrderResponse;
import com.example.onetomany.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{



//	Customer save(Customer customer);
	
	@Query("select new com.example.onetomany.dto.OrderResponse(c.name , p.productName) from Customer c join c.products p")
	public List<OrderResponse>  getJoinInfomation();

}
