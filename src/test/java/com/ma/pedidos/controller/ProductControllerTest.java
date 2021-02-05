package com.ma.pedidos.controller;

import com.ma.pedidos.dto.ProductDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

class ProductControllerTest {

    @DisplayName("Save product success")
    @Test
    public void savaProduct_success(){

        String request = """
                {
                    "nombre": "Jamón y morrones",
                    "descripcionCorta" : "Pizza de jamón y morrones",
                    "descripcionLarga" : "Mozzarella, jamón, morrones y aceitunas verdes",
                    "precioUnitario" : 500.00\s
               }
                """;

        with().body(request)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/v1/products/save")
                .then()
                .statusCode(201);
    }

    @DisplayName("Save product success")
    @Test
    public void savaProduct_error(){


        String request = """
                {
                    "descripcionCorta" : "Pizza de jamón y morrones",
                    "descripcionLarga" : "Mozzarella, jamón, morrones y aceitunas verdes",
                    "precioUnitario" : 500.00\s
               }
                """;

        with().body(request)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/v1/products/save")
                .then()
                .statusCode(400);
    }

    @DisplayName("Search Product by Id")
    @Test
    public void searchProduct_byId_success() {
        get("/api/v1/products/44394ddf-ab6e-4e2f-88f8-7f7554216d7e").
                then()
                .statusCode(200).assertThat()
                .body("id", equalTo("44394ddf-ab6e-4e2f-88f8-7f7554216d7e"));
    }

    @DisplayName("Search Product by Id, not found")
    @Test
    public void searchProduct_byId_notFound() {
        get("/api/v1/products/44394ddf-ab6e-4e2f-88f8-7f7554216d75").then().statusCode(404).assertThat()
                .body("Errores[0].error", equalTo("Producto no encontrado, con id: 44394ddf-ab6e-4e2f-88f8-7f7554216d75"));
    }




}