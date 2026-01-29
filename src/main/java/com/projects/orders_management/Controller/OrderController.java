package com.projects.orders_management.Controller;

import com.projects.orders_management.DTO.OrderRequestDTO;
import com.projects.orders_management.DTO.OrderResponseDTO;
import com.projects.orders_management.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO dto){
        return orderService.createOrder(dto);
    }

    @GetMapping
    public List<OrderResponseDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/customers/{customerId}")
    public List<OrderResponseDTO> getOrdersByCustomer(@PathVariable Long customerId){
        return orderService.getOrdersByCustomers(customerId);
    }

}
