package com.ma.pedidos.controller;

import com.ma.pedidos.dto.ProductDto;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDto searchProductById(@PathVariable UUID id) throws ServiceException {
        logger.info("Search Product by {}", id);

        return productService.searchProductById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveProduct(@Valid @RequestBody ProductDto productDto){
        logger.info("Save Product");

        return productService.saveProduct(productDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDto updateProduct(@PathVariable("id") UUID id, @Valid @RequestBody ProductDto productDto) throws ServiceException {

        logger.info("Update Product by id: {}", id);

        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) throws ServiceException {

        logger.info("Delete product by id: {}", id);

        productService.deleteProduct(id);
    }
}
