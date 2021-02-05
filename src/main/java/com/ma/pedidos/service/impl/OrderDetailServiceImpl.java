package com.ma.pedidos.service.impl;

import com.ma.pedidos.model.OrderDetail;
import com.ma.pedidos.repository.OrderDetailRepository;
import com.ma.pedidos.service.OrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public void saveOrderDetails(List<OrderDetail> orderDetails) {
        logger.info("save all OrderDetails");

        orderDetailRepository.saveAll(orderDetails);
    }
}
