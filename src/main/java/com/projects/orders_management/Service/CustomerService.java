package com.projects.orders_management.Service;

import com.projects.orders_management.Model.Customer;
import com.projects.orders_management.Repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){
        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found with id: " + id));
    }

    public Customer updateCustomer(Long id, Customer customer){
        Customer newCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        newCustomer.setUsername(customer.getUsername());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPhone(customer.getPhone());

        return customerRepository.save(newCustomer);
    }

    public void deleteCustomer(Long id){
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }
}