package com.projects.orders_management.DTO.order;

import com.projects.orders_management.Model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderResponse {

    private Long orderId;
    private OrderStatus status;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private List<OrderItemResponse> items;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
}
