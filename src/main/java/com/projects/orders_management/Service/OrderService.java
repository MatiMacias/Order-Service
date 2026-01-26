package com.projects.orders_management.Service;

import com.projects.orders_management.DTO.OrderRequestDTO;
import com.projects.orders_management.DTO.OrderResponseDTO;
import com.projects.orders_management.Model.Customer;
import com.projects.orders_management.Model.Order;
import com.projects.orders_management.Model.OrderStatus;
import com.projects.orders_management.Repository.CustomerRepository;
import com.projects.orders_management.Repository.OrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO dto){
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Order order = new Order();
        order.setDate(dto.getDate());
        order.setStatus(OrderStatus.valueOf(dto.getStatus()));
        order.setCustomer(customer);

        Order saved = orderRepository.save(order);

        return mapToResponse(saved);
    }

    public List<OrderResponseDTO> getAllOrders(){
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    //
    public List<OrderResponseDTO> getOrdersByCustomers(Long customerId){
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    //

    public OrderResponseDTO mapToResponse(Order order){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setStatus(order.getStatus().name());
        dto.setCustomerId(order.getCustomer().getId());
        return dto;
    }
}
