package com.ma.pedidos.service;

import com.ma.pedidos.dto.ProductDto;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.model.Product;

import java.util.UUID;

public interface ProductService {

    /**
     * Search Product by Id
     * and returns ProductDto.
     *
     * @param id {@link UUID}
     * @return {@link ProductDto}
     * @throws ServiceException
     */
    ProductDto searchProductById(UUID id) throws ServiceException;

    /**
     * Find Product by Id.
     *
     * @param id {@link UUID}
     * @return {@link Product}
     * @throws ServiceException
     */
    Product findProductById(UUID id) throws ServiceException;

    /**
     * Save Product.
     *
     * @param productDto {@link ProductDto}
     * @return {@link ProductDto}
     */
    ProductDto saveProduct(ProductDto productDto);

    /**
     * Update Product.
     *
     * @param id {@link UUID}
     * @param productDto {@link ProductDto}
     * @return {@link ProductDto}
     * @throws ServiceException
     */
    ProductDto updateProduct(UUID id, ProductDto productDto) throws ServiceException;

    /**
     * Delete Product.
     *
     * @param id {@link UUID}
     * @throws ServiceException
     */
    void deleteProduct(UUID id) throws ServiceException;
}
