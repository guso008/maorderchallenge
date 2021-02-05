package com.ma.pedidos.service;

import com.ma.pedidos.dto.OrderDto;
import com.ma.pedidos.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    /**
     * Save Order.
     *
     * @param orderDto {@link OrderDto}
     * @return {@link OrderDto}
     * @throws ServiceException
     */
    OrderDto saveOrder(OrderDto orderDto) throws ServiceException;

    /**
     * Ger Order by date.
     *
     * @param date {@link LocalDate}
     * @return {List<OrderDto>}
     */
    List<OrderDto> getOrderByDate(LocalDate date);
}
