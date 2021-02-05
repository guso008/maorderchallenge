package com.ma.pedidos.controller;

import com.ma.pedidos.dto.ProductDto;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Api(tags = { "Product" })
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @ApiOperation("Obtener producto por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProductDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found") })
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDto searchProductById(@PathVariable UUID id) throws ServiceException {
        logger.info("Search Product by {}", id);

        return productService.searchProductById(id);
    }

    @ApiOperation("Crear un producto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ProductDto.class),
            @ApiResponse(code = 400, message = "Bad Request") })
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveProduct(@Valid @RequestBody ProductDto productDto){
        logger.info("Save Product");

        return productService.saveProduct(productDto);
    }

    @ApiOperation("Actualizar producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProductDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found") })
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProductDto updateProduct(@PathVariable("id") UUID id, @Valid @RequestBody ProductDto productDto) throws ServiceException {

        logger.info("Update Product by id: {}", id);

        return productService.updateProduct(id, productDto);
    }

    @ApiOperation("Eliminar producto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProductDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found") })
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) throws ServiceException {

        logger.info("Delete product by id: {}", id);

        productService.deleteProduct(id);
    }
}
