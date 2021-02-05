package com.ma.pedidos.service;

import com.ma.pedidos.dto.ProductDto;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.model.Product;

import java.util.UUID;

public interface ProductService {
    ProductDto searchProductById(UUID id) throws ServiceException;

    Product findProductById(UUID id) throws ServiceException;

    ProductDto saveProduct(ProductDto productDto);

    ProductDto updateProduct(UUID id, ProductDto productDto) throws ServiceException;

    void deleteProduct(UUID id) throws ServiceException;
}
