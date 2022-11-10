package com.example.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onetomany.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	

}
