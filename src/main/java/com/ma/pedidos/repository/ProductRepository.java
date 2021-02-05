package com.ma.pedidos.repository;

import com.ma.pedidos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    public Product findByIdAndDeleteDateIsNull(UUID id);
    public boolean existsProductByIdAndDeleteDateIsNull(UUID id);
}
