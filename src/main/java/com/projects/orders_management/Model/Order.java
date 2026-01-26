package com.projects.orders_management.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
// se le aplica nombre manual porque order es una palabra reservada en SQL
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "customer_id", nullable = false)
    private Customer customer;

}
