package com.projects.orders_management.Service;

import com.projects.orders_management.DTO.customer.CustomerCreateRequest;
import com.projects.orders_management.DTO.customer.CustomerResponse;
import com.projects.orders_management.DTO.customer.CustomerUpdateRequest;
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

    public CustomerResponse createCustomer(CustomerCreateRequest request){
        if(customerRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        Customer customer= new Customer();
        customer.setUsername(request.getName());
        customer.setEmail(request.getEmail());

        Customer saved = customerRepository.save(customer);

        return mapToResponse(saved);
    }

    public List<CustomerResponse> getAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CustomerResponse findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        return mapToResponse(customer);
    }

    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest request){
        Customer newCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        newCustomer.setUsername(request.getUsername());
        newCustomer.setEmail(request.getEmail());

        Customer update = customerRepository.save(newCustomer);

        return mapToResponse(update);
    }

    public void deleteCustomer(Long id){
        if(!customerRepository.existsById(id)){
            throw new RuntimeException("Customer not foud");
        }
        customerRepository.deleteById(id);
    }


    private CustomerResponse mapToResponse(Customer customer){
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setUsername(customer.getUsername());
        response.setEmail(customer.getEmail());
        return response;
    }
}