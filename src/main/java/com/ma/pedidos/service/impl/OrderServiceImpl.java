package com.ma.pedidos.service.impl;

import com.ma.pedidos.dto.OrderDto;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.mapper.OrderDetailMapper;
import com.ma.pedidos.mapper.OrderMapper;
import com.ma.pedidos.model.Order;
import com.ma.pedidos.model.OrderDetail;
import com.ma.pedidos.model.enums.OrderStatus;
import com.ma.pedidos.repository.OrderRepository;
import com.ma.pedidos.service.OrderDetailService;
import com.ma.pedidos.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Value("${pedidos.descuento.cantidad-aplicar}")
    private Integer amountApplyDiscount;

    @Value("${pedidos.descuento.porcentaje-aplicar}")
    private Double percentageApplyDiscount;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDto saveOrder(OrderDto orderDto) throws ServiceException {
        Order order = orderMapper.convertToEntity(orderDto);

        order.setStatus(OrderStatus.PENDIENTE);

        List<OrderDetail> orderDetails = orderDetailMapper.convertToEntityList(orderDto.getOrderDetailDtos());
        order.setDetails(orderDetails);


        completeAttributesOrder(order, orderDetails);

        order = orderRepository.save(order);

        if (!CollectionUtils.isEmpty(orderDetails))
            saveOrderDetails(order, orderDetails);

        return orderMapper.convertToDto(order);
    }

    @Override
    public List<OrderDto> getOrderByDate(LocalDate date) {
        List<Order> orderList = orderRepository.findByDateIs(date);

        return orderMapper.convertToDtoList(orderList);
    }

    private void completeAttributesOrder(Order order, List<OrderDetail> orderDetails) {
        /*
         * if orderDetails is empty then end method
         */
        if (CollectionUtils.isEmpty(orderDetails))
            return;

        boolean isDiscount = false;

        Integer amountProduct = 0;

        Double total = 0D;

        for (OrderDetail orderDetail : orderDetails) {

            amountProduct += orderDetail.getQuantity();

            total += orderDetail.getUnitPrice() * orderDetail.getQuantity();
        }

        /*
         * evaluate if it has a discount
         */
        if (percentageApplyDiscount != null && amountApplyDiscount != null && amountProduct > amountApplyDiscount) {

            logger.info("order with discount");

            isDiscount = true;

            total -= (total * percentageApplyDiscount) / 100;
        }

        order.setTotalAmount(total);
        order.setApplyDiscount(isDiscount);
    }

    @Override
    public Optional<Order> searchOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    private void saveOrderDetails(Order order, List<OrderDetail> orderDetails) {

        orderDetails.forEach(orderDetail -> orderDetail.setOrder(order));

        orderDetailService.saveOrderDetails(orderDetails);
    }

}
