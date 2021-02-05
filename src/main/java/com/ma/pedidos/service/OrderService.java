package com.ma.pedidos.service;

import com.ma.pedidos.dto.OrderDto;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    Optional<Order> searchOrderById(UUID id);


    OrderDto saveOrder(OrderDto orderDto) throws ServiceException;

    List<OrderDto> getOrderByDate(LocalDate date);
}
