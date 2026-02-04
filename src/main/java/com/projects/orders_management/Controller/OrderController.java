package com.projects.orders_management.Controller;

import com.projects.orders_management.DTO.order.OrderCreateRequest;
import com.projects.orders_management.DTO.order.OrderRequestDTO;
import com.projects.orders_management.DTO.order.OrderResponse;
import com.projects.orders_management.DTO.order.OrderResponseDTO;
import com.projects.orders_management.Service.OrderService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request){
        return orderService.createOrder(request);
    }

    @GetMapping
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/customers/{customerId}")
    public List<OrderResponse> getOrdersByCustomer(@PathVariable Long customerId){
        return orderService.getOrdersByCustomers(customerId);
    }

}
