package com.ma.pedidos.controller;

import com.ma.pedidos.dto.OrderDto;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderService orderService;

    @PostMapping("/save")
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderDto saveOrder(@Valid @RequestBody OrderDto orderDto) throws ServiceException {

        logger.info("Save Order");

        return orderService.saveOrder(orderDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<OrderDto> getOrderByDate(
            @Valid @NotNull(message = "La fecha no puede ser vacía")
            @RequestParam(name = "fecha", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        logger.info("Get orders");

        return orderService.getOrderByDate(date);
    }
}
