package com.projects.orders_management.Controller;

import com.projects.orders_management.DTO.customer.CustomerCreateRequest;
import com.projects.orders_management.DTO.customer.CustomerResponse;
import com.projects.orders_management.DTO.customer.CustomerUpdateRequest;
import com.projects.orders_management.Model.Customer;
import com.projects.orders_management.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse createCustomer(
            @Valid @RequestBody CustomerCreateRequest request){
        return customerService.createCustomer(request);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id){
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerUpdateRequest request){

        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

}
