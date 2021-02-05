package com.ma.pedidos.repository;

import com.ma.pedidos.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT o FROM order_head o WHERE o.createdDate >= :dateInit and o.createdDate < :dateEnd")
    List<Order> findByDateIs(@Param("dateInit") Date dateInit, @Param("dateEnd") Date dateEnd);

}
