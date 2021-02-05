package com.ma.pedidos.repository;

import com.ma.pedidos.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT o FROM order_head o WHERE o.createdDate = :date ")
    List<Order> findByDateIs(@Param("date") LocalDate date);

}
