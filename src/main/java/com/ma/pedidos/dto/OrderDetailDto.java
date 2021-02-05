package com.ma.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailDto {

    @NotNull(message = "EL producto no puede estar vació")
    @JsonProperty("producto")
    private UUID productId;

    @NotNull(message = "La cantidad del producto no puede estar vacía")
    @Positive(message = "La cantidad del producto debe ser mayor a 0")
    @JsonProperty("cantidad")
    private Integer quantity;

    @JsonProperty("name")
    private String name;

    @JsonProperty("importe")
    private double amount;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
