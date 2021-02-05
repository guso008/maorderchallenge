package com.ma.pedidos.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OrderControllerTest {

    private static final String goodOrderRequest = """
                                    {
                                        "direccion": "Dorton Road 80",
                                        "email": "tsayb@opera.com", 
                                        "telefono": "(0351) 48158101",
                                        "horario": "21:00",
                                        "detalle": [
                                         { "producto": "44394ddf-ab6e-4e2f-88f8-7f7554216d7e", 
                                           "cantidad": 1 },
                                         { "producto": "44394ddf-ab6e-4e2f-88f8-7f7554216d71", 
                                           "cantidad": 1 }]
                                    }
                                    """;

    private static final String goodOrderApplyDiscountRequest = """
                                                    {
                                                        "direccion": "Dorton Road 80",
                                                        "email": "tsayb@opera.com",\s
                                                        "telefono": "(0351) 48158101",
                                                        "horario": "21:00",
                                                        "detalle": [
                                                         { "producto": "44394ddf-ab6e-4e2f-88f8-7f7554216d7e",\s
                                                           "cantidad": 2 },
                                                         { "producto": "44394ddf-ab6e-4e2f-88f8-7f7554216d71",\s
                                                           "cantidad": 2 }]
                                                       } 
                                                  """;

    private static final String badOrderRequest = """
                                                    {
                                                        "email": "tsayb@opera.com",\s
                                                        "telefono": "(0351) 48158101",
                                                        "horario": "21:00",
                                                        "detalle": [
                                                         { "producto": "89efb206-2aa6-4e21-8a23-5765e3de1f31" },
                                                         { "producto": "e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1",\s
                                                           "cantidad": 2 }]
                                                    }
                                                  """;

    @DisplayName("Save order success")
    @Test
    public void saveOrder_success(){

        with().body(goodOrderRequest)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/v1/orders/save")
                .then()
                .statusCode(201)
                .assertThat()
                .body("estado", equalTo("PENDIENTE"));;
    }

    @DisplayName("Save order success apply discount")
    @Test
    public void saveOrder_success_applyDiscount(){

        with().body(goodOrderApplyDiscountRequest)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/v1/orders/save")
                .then()
                .statusCode(201)
                .assertThat()
                .body("descuento", equalTo(true));;
    }

    @DisplayName("Save order fail")
    @Test
    public void saveOrder_fail(){

        with().body(badOrderRequest)
                .contentType(ContentType.JSON)
                .when()
                .request("POST", "/api/v1/orders/save")
                .then()
                .statusCode(400)
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @DisplayName("Search Order by Date")
    @Test
    public void searchProduct_byId_success() {
        get("/api/v1/orders?fecha=2021-02-05").
                then()
                .statusCode(200)
                .assertThat()
                .body("size()", greaterThan(0));
    }

}
