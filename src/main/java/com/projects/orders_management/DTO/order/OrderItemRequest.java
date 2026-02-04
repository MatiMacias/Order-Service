package com.projects.orders_management.DTO.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemRequest {

    @NotNull
    private Long productId;

    @Positive
    private Integer quantity;

    public @NotNull Long getProductId() {
        return productId;
    }

    public void setProductId(@NotNull Long productId) {
        this.productId = productId;
    }

    public @Positive Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@Positive Integer quantity) {
        this.quantity = quantity;
    }
}
