package com.ma.pedidos.mapper;

import com.ma.pedidos.dto.OrderDetailDto;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.model.OrderDetail;
import com.ma.pedidos.model.Product;
import com.ma.pedidos.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductService productService;

    public List<OrderDetail> convertToEntityList(List<OrderDetailDto> orderDetailDtos) throws ServiceException {

        if (CollectionUtils.isEmpty(orderDetailDtos))
            return Collections.emptyList();

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (OrderDetailDto orderDetailDto : orderDetailDtos) {
            orderDetails.add(convertToEntity(orderDetailDto));
        }

        return orderDetails;
    }

    public OrderDetail convertToEntity(OrderDetailDto orderDetailDto) throws ServiceException {

        if (orderDetailDto == null)
            return null;

        OrderDetail orderDetail = mapper.map(orderDetailDto, OrderDetail.class);

        Product product = productService.findProductById(orderDetailDto.getProductId());

        orderDetail.setProduct(product);
        orderDetail.setUnitPrice(product.getUnitPrice());

        return orderDetail;
    }

    public OrderDetailDto convertToDto(OrderDetail orderDetail) {

        if (orderDetail == null)
            return null;

        OrderDetailDto orderDetailDto = mapper.map(orderDetail, OrderDetailDto.class);

        Product product = orderDetail.getProduct();

        if (product != null){
            orderDetailDto.setProductId(product.getId());
            orderDetailDto.setName(product.getName());
            orderDetailDto.setAmount(product.getUnitPrice() * orderDetail.getQuantity());
        }
        return orderDetailDto;
    }

    public List<OrderDetailDto> convertToDtoList(List<OrderDetail> orderDetails) {
        if (CollectionUtils.isEmpty(orderDetails))
            return Collections.emptyList();

        return orderDetails.stream().map(this::convertToDto).collect(Collectors.toList());
    }


}
