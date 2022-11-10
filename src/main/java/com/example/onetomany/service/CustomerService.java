package com.example.onetomany.service;

import com.example.onetomany.entity.Customer;
import com.example.onetomany.entity.Product;
import com.example.onetomany.model.CustomerRequest;
import com.example.onetomany.model.CustomerResponse;
import com.example.onetomany.model.ProductRequest;
import com.example.onetomany.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Transactional
    public Customer addNewCustomer(CustomerRequest customerRequest){
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setGender(customerRequest.getGender());


        List<Product> list = new ArrayList<>();
        for (ProductRequest pro : customerRequest.getProducts() )
            {
                Product product = new Product();
                product.setProductName(pro.getProductName());
                product.setQty(pro.getQty());
                product.setPrice(pro.getPrice());
                product.setCustomer(customer);
                list.add(product);

        }
        customer.setProducts(list);
        Customer cus = customerRepository.save(customer);

//        CustomerResponse response = new CustomerResponse();
//        BeanUtils.copyProperties(cus,response);
        return cus;
    }
    @ReadOnlyProperty
    public Customer getOneCustomer(Long id){
        Optional<Customer> opt = customerRepository.findById(id);
        if (opt.isPresent()) {
            Customer customer = opt.get();
            return  customer;
        }
        throw  new RuntimeException("ko tim thay voi id:" + id);

    }
    @ReadOnlyProperty
    public Page<Customer> getAll (Pageable pageable){
        Page<Customer> list = customerRepository.findAll(pageable);
        return  list;

    }
    @Transactional
    public Customer update (Long id, CustomerRequest customerRequest){
        Optional<Customer> opt = customerRepository.findById(id);
        if (opt.isPresent()) {
        Customer customer = opt.get();

        customer.setName(customerRequest.getName());
        customer.setGender(customerRequest.getGender());
        customer.setEmail(customerRequest.getEmail());
        List<Product> list = customer.getProducts();
            for (ProductRequest pro: customerRequest.getProducts()) {
//                for (Product product : list) {
////                Product product = new Product();
//
//                    product.setProductName(pro.getProductName());
//                    product.setQty(pro.getQty());
//                    product.setPrice(pro.getPrice());
//                    product.setCustomer(customer);
//
//                }

                list.stream().filter(l -> l.equals(pro.get))
            }
//            customer.setProducts(list);
        Customer cus = customerRepository.save(customer);
            return  cus;
        }else {
            throw  new RuntimeException("o tim thay voi id: " + id);
        }

    }

    public String deleteCustomer(Long id){
        Optional<Customer> opt = customerRepository.findById(id);
        if (opt.isPresent()) {
            customerRepository.deleteById(id);
            return "xoa thanh cong";
        }else {
            throw  new RuntimeException("o tim thay voi id: " + id);
        }
    }

}
