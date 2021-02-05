package com.ma.pedidos.service;

import com.ma.pedidos.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void saveOrderDetails(List<OrderDetail> orderDetails);
}
