package com.ma.pedidos.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.ma.pedidos.dto.ProductDto;
import com.ma.pedidos.mapper.ProductMapper;
import com.ma.pedidos.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;;

import com.ma.pedidos.model.Product;
import com.ma.pedidos.repository.ProductRepository;
import com.ma.pedidos.service.impl.ProductServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;


class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

	@MockBean
	private ModelMapper modelMapper;

	@Mock
	ProductMapper productMapper;

	@DisplayName("Save product success")
	@Test
	void saveProduct_success() {

		ProductDto productDto = getProductDto();

		Product product = getProduct();

		when(productMapper.convertToEntity(productDto))
				.thenReturn(product);

		when(productRepository.save(product))
				.thenReturn(product);

		when(productMapper.convertToDto(product))
				.thenReturn(productDto);

		assertNotNull(productServiceImpl.saveProduct(productDto));
	}

	@DisplayName("Search Product exception")
	@Test
	void searchProductById_theReturnException() {

		when(productRepository.findByIdAndDeleteDateIsNull(UUID.randomUUID())).thenReturn(null);

		Assertions.assertThrows(ServiceException.class, () -> productServiceImpl.searchProductById(UUID.randomUUID()));
	}

	@DisplayName("Search Product success")
	@Test
	void searchProductById_success() throws ServiceException {

		UUID id = UUID.randomUUID();

		Product product = getProduct();

		when(productRepository.findByIdAndDeleteDateIsNull(id)).thenReturn(product);

		ProductDto productDto = getProductDto();

		when(productMapper.convertToDto(product))
				.thenReturn(productDto);

		assertNotNull(productServiceImpl.searchProductById(id));
	}

	@DisplayName("Search Product exception")
	@Test
	void deleteProduct_theReturnException() {

		UUID id = UUID.randomUUID();

		when(productRepository.findByIdAndDeleteDateIsNull(id)).thenReturn(null);

		Assertions.assertThrows(ServiceException.class, () -> productServiceImpl.deleteProduct(id));
	}

	@DisplayName("Search Product success")
	@Test
	void deleteProduct_theReturnOk() throws ServiceException {

		UUID id = UUID.randomUUID();

		Product product = getProduct();

		when(productRepository.findByIdAndDeleteDateIsNull(id)).thenReturn(product);

		productServiceImpl.deleteProduct(id);

		verify(productRepository, times(1)).save(product);

	}

	private ProductDto getProductDto() {

		ProductDto productDto = new ProductDto();

		productDto.setName("pizza");
		productDto.setShortDescription("comun");
		productDto.setUnitPrice(500.0D);

		return productDto;
	}

	private Product getProduct(){
		Product product = new Product();
		product.setName("pizza");
		product.setShortDescription("comun");
		product.setUnitPrice(500.0);
		return product;
	}

}
