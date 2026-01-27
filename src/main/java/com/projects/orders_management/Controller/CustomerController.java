package com.projects.orders_management.Controller;

import com.projects.orders_management.Model.Customer;
import com.projects.orders_management.Service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{/id}")
    public Customer findById(@RequestBody Long id){
        return customerService.findById(id);
    }

    @PutMapping("{/id}")
    public Customer updateCustomer(@PathVariable Long id,@RequestBody Customer customer){

        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("{/id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

}
