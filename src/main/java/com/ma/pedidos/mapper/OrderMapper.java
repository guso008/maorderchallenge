package com.ma.pedidos.mapper;

import com.ma.pedidos.dto.OrderDetailDto;
import com.ma.pedidos.dto.OrderDto;
import com.ma.pedidos.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public Order convertToEntity(OrderDto orderHearderDto) {

        return mapper.map(orderHearderDto, Order.class);
    }

    public OrderDto convertToDto(Order order) {

        OrderDto orderDto = mapper.map(order, OrderDto.class);

        List<OrderDetailDto> orderDetailDtos = orderDetailMapper.convertToDtoList(order.getDetails());

        orderDto.setOrderDetailDtos(orderDetailDtos);

        return orderDto;
    }

    public List<OrderDto> convertToDtoList(List<Order> orderList) {
        if (CollectionUtils.isEmpty(orderList))
            return Collections.emptyList();

        return orderList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
