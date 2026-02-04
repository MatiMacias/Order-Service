package com.projects.orders_management.Service;

import com.projects.orders_management.DTO.order.*;
import com.projects.orders_management.Model.*;
import com.projects.orders_management.Repository.CustomerRepository;
import com.projects.orders_management.Repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productService = productService;
    }

    public OrderResponse createOrder(OrderCreateRequest request){
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found"
                ));
        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(var itemRequest : request.getItems()){
            Product product = productService.getProductEntityById(itemRequest.getProductId());

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setUnitPrice(product.getPrice());

            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf((itemRequest.getQuantity())));

            item.setSubtotal(subtotal);

            total = total.add(subtotal);
            items.add(item);
        }

        order.setItems(items);
        order.setTotal(total);

        Order saved = orderRepository.save(order);

        return mapToResponse(saved);
    }

    public List<OrderResponse> getAllOrders(){
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    //
    public List<OrderResponse> getOrdersByCustomers(Long customerId){
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    //

    public OrderResponse mapToResponse(Order order){

        List<OrderItemResponse> itemResponse = order.getItems().stream().map(item -> {
            OrderItemResponse response = new OrderItemResponse();
            response.setProductName(item.getProduct().getName());
            response.setUnitPrice(item.getUnitPrice());
            response.setQuantity(item.getQuantity());
            response.setSubtotal(item.getSubtotal());
            return response;
        }).toList();

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setOrderDate(order.getCreatedAt().toLocalDate());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotal());
        response.setItems(itemResponse);

        return response;
    }
}
