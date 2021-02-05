package com.ma.pedidos.service.impl;

import com.ma.pedidos.constant.ProductConstant;
import com.ma.pedidos.dto.ProductDto;
import com.ma.pedidos.mapper.ProductMapper;
import com.ma.pedidos.model.Product;
import com.ma.pedidos.repository.ProductRepository;
import com.ma.pedidos.service.ProductService;
import com.ma.pedidos.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public ProductDto searchProductById(UUID id) throws ServiceException {
        Product product = this.findProductById(id);

        return productMapper.convertToDto(product);
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {

        Product product = productRepository.save(productMapper.convertToEntity(productDto));

        return productMapper.convertToDto(product);
    }

    @Override
    public ProductDto updateProduct(UUID id, ProductDto productDto) throws ServiceException {

        if(existProduct(id)){
            logger.warn("Product not found, id: {}", id);
            throw new ServiceException(String.format(ProductConstant.PRODUCT_NOT_FOUND, id), HttpStatus.NOT_FOUND);
        }

        Product product = productMapper.convertToEntity(productDto);
        product.setId(id);
        product = productRepository.save(productMapper.convertToEntity(productDto));

        return productMapper.convertToDto(product);
    }

    @Override
    public void deleteProduct(UUID id) throws ServiceException {

        Product product = findProductById(id);
        product.setDeleteDate(Date.from(Instant.now()));

        productRepository.save(product);
    }

    @Override
    public Product findProductById(UUID id) throws ServiceException {
        Product product = productRepository.findByIdAndDeleteDateIsNull(id);

        if(product == null){
            logger.warn("Product not found, id: {}", id);
            throw new ServiceException(String.format(ProductConstant.PRODUCT_NOT_FOUND, id), HttpStatus.NOT_FOUND);
        }

        return product;
    }

    /**
     * Verify exist Product.
     *
     * @param id {@link UUID}
     * @return {@link boolean}
     */
    private boolean existProduct(UUID id) {
        return productRepository.existsProductByIdAndDeleteDateIsNull(id);
    }
}
