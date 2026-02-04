package com.projects.orders_management.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
// se le aplica nombre manual porque order es una palabra reservada en SQL
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

    @PrePersist
    void prePersist(){
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.CREATED;
    }



}
