package org.example.api.store;

import org.example.api.BaseTest;
import org.example.model.Order;
import org.example.model.Inventory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class StoreApiTest extends BaseTest {

    Date current = new Date();
    Order order = new Order();

    @Test(  priority = 1)
    public void placeOrderTest() throws InterruptedException {

        order.setId(System.getProperty("orderId"));
        order.setPetId(173389);
        order.setQuantity(20);
        order.setShipDate(String.valueOf(current.getTime()));
        order.setStatus("placed");
        order.setComplete(true);
        given()
                .body(order).when()
                .post("/store/order")
                .then()
                .statusCode(200);

        Thread.sleep(10000);
        Order actual =
                given()
                        .pathParam("orderId", System.getProperty("orderId"))
                        .when()
                        .get("/store/order/{orderId}")
                        .then()
                        .statusCode(200)
                        .extract().body()
                        .as(Order.class);
        Assert.assertEquals(actual, order);
        System.setProperty("orderId", String.valueOf(order.getId()));

    }
    @Test(  priority = 2)
    public void inventoryTest(){
        Inventory actual = given()
                .when()
                .get("/store/inventory")
                .then()
                .statusCode(200)
                .extract().body()
                .as(Inventory.class);
        Assert.assertTrue(actual.getSold()!=null, "Inventory не содержит статус sold" );

    }

    @Test (  priority = 3)
    public void deleteOrderTest() throws IOException, InterruptedException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("my.properties"));
        given()
                .pathParam("orderId", System.getProperty("orderId"))
                .when()
                .delete("/store/order/{orderId}")
                .then()
                .statusCode(200);
        Thread.sleep(10000);
        given()
                .pathParam("orderId", System.getProperty("orderId"))
                .when()
                .get("/store/order/{orderId}")
                .then()
                .statusCode(404);

    }
}
