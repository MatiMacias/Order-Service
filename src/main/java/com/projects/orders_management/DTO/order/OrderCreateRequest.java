package com.projects.orders_management.DTO.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderCreateRequest {

    @NotNull
    private Long customerId;

    @NotEmpty
    private List<OrderItemRequest> items;

    public @NotNull Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(@NotNull Long customerId) {
        this.customerId = customerId;
    }

    public @NotEmpty List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(@NotEmpty List<OrderItemRequest> items) {
        this.items = items;
    }
}
