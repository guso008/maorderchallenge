package com.ma.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    @ApiModelProperty(hidden = true)
    @JsonProperty("id")
    private UUID id;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
