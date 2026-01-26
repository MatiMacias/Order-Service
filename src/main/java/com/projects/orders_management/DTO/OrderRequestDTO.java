package com.projects.orders_management.DTO;

import com.projects.orders_management.Model.OrderStatus;

import java.time.LocalDate;

public class OrderRequestDTO {

    private LocalDate date;
    private String status;
    private Long customerId;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
