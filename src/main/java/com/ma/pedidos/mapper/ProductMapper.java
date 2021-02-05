package com.ma.pedidos.mapper;

import com.ma.pedidos.dto.ProductDto;
import com.ma.pedidos.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
