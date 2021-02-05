package com.ma.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ma.pedidos.model.enums.OrderStatus;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    @NotBlank(message = "La dirección no debe ser vacío o null")
    @JsonProperty("direccion")
    private String address;

    @Email
    @JsonProperty("email")
    private String email;

    @JsonProperty("telefono")
    private String phone;

    @NotNull(message = "El horario no puede ser vació")
    @JsonProperty("horario")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime orderTime;

    @Valid
    @NotEmpty(message = "El detalle no puede ser vacio")
    @JsonProperty("detalle")
    List<OrderDetailDto> orderDetailDtos;

    @JsonProperty("total")
    @ApiModelProperty(hidden = true)
    private Double totalAmount;

    @JsonProperty("descuento")
    @ApiModelProperty(hidden = true)
    private boolean applyDiscount;

    @JsonProperty("estado")
    @ApiModelProperty(hidden = true)
    private OrderStatus status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderDetailDto> getOrderDetailDtos() {
        return orderDetailDtos;
    }

    public void setOrderDetailDtos(List<OrderDetailDto> orderDetailDtos) {
        this.orderDetailDtos = orderDetailDtos;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isApplyDiscount() {
        return applyDiscount;
    }

    public void setApplyDiscount(boolean applyDiscount) {
        this.applyDiscount = applyDiscount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
