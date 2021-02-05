package com.ma.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    @NotBlank
    @JsonProperty("nombre")
    private String name;

    @NotBlank
    @JsonProperty("descripcionCorta")
    private String shortDescription;

    @JsonProperty("descripcionLarga")
    private String largeDescription;

    @NotNull
    @JsonProperty("precioUnitario")
    @Min(value = 0, message = "debe ser mayor que o igual a 0.0D")
    private Double unitPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLargeDescription() {
        return largeDescription;
    }

    public void setLargeDescription(String largeDescription) {
        this.largeDescription = largeDescription;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
