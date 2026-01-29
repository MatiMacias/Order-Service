package com.projects.orders_management.Repository;

import com.projects.orders_management.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
