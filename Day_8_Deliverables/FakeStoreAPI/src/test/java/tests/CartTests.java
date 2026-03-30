package tests;

import base.BaseTest;
import endpoints.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Cart;
import utils.TestDataBuilder;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature("Carts")
public class CartTests extends BaseTest {

    private static final int CART_ID = 1;

    // ── TC_CART_01 ────────────────────────────────────────────────────────────
    @Test(priority = 1)
    @Story("Get All Carts")
    @Description("GET /carts must return 200 and a list of 7 carts each having id, userId, date and products")
    public void TC_CART_01_getAllCarts_return200WithFullList() {
        Response response = given()
                .spec(requestSpec)
            .when()
                .get(Endpoints.CARTS)
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("$",        not(empty()))
                .body("id",       everyItem(notNullValue()))
                .body("userId",   everyItem(notNullValue()))
                .body("date",     everyItem(notNullValue()))
                .body("products", everyItem(not(empty())))
                .extract().response();

        List<Cart> carts = response.jsonPath().getList("$", Cart.class);

        Assert.assertFalse(carts.isEmpty(),          "Cart list must not be empty");
        Assert.assertEquals((int) carts.size(), 7,   "FakeStore always returns 7 carts");

        Cart first = carts.get(0);
        Assert.assertNotNull(first.getId(),           "id must not be null");
        Assert.assertNotNull(first.getUserId(),       "userId must not be null");
        Assert.assertNotNull(first.getDate(),         "date must not be null");
        Assert.assertFalse(first.getProducts().isEmpty(), "products list must not be empty");
    }

    // ── TC_CART_02 ────────────────────────────────────────────────────────────
    @Test(priority = 2)
    @Story("Get Single Cart")
    @Description("GET /carts/{id} must return 200 with id=1, userId=1, a valid date string and 3 cart items")
    public void TC_CART_02_getCartById_returnCorrectCartWithAllFields() {
        Response response = given()
                .spec(requestSpec)
                .pathParam("id", CART_ID)
            .when()
                .get(Endpoints.CART_BY_ID)
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id",                    equalTo(CART_ID))
                .body("userId",                equalTo(1))
                .body("date",                  equalTo("2020-03-02T00:00:00.000Z"))
                .body("products",              hasSize(3))
                .body("products[0].productId", equalTo(1))
                .body("products[0].quantity",  equalTo(4))
                .body("products[1].productId", equalTo(2))
                .body("products[1].quantity",  equalTo(1))
                .body("products[2].productId", equalTo(3))
                .body("products[2].quantity",  equalTo(6))
                .extract().response();

        Cart cart = response.as(Cart.class);
        Assert.assertNotNull(cart.getProducts(),                        "products list must be present");
        Assert.assertEquals((int) cart.getProducts().size(), 3,         "cart 1 must have exactly 3 products");
        Assert.assertEquals((int) cart.getProducts().get(0).getProductId(), 1, "first productId must be 1");
    }

    // ── TC_CART_03 ────────────────────────────────────────────────────────────
    @Test(priority = 3)
    @Story("Create Cart")
    @Description("POST /carts must return 201 and echo back the userId and products list")
    public void TC_CART_03_createCart_return201WithCreatedCart() {
        Cart payload = TestDataBuilder.newCart(3, 5, 2);

        given()
            .spec(requestSpec)
            .body(payload)
        .when()
            .post(Endpoints.CARTS)
        .then()
            .spec(responseSpec)
            .statusCode(201)
            .body("id",     notNullValue())
            .body("userId", equalTo(payload.getUserId()));
    }

    // ── TC_CART_04 ────────────────────────────────────────────────────────────
    @Test(priority = 4)
    @Story("Update Cart")
    @Description("PUT /carts/{id} must return 200 and reflect the updated userId in the response")
    public void TC_CART_04_updateCart_return200WithUpdatedFields() {
        Cart payload = TestDataBuilder.newCart(7, 2, 10);

        given()
            .spec(requestSpec)
            .pathParam("id", CART_ID)
            .body(payload)
        .when()
            .put(Endpoints.CART_BY_ID)
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("userId", equalTo(payload.getUserId()));
    }

    // ── TC_CART_05 ────────────────────────────────────────────────────────────
    @Test(priority = 5)
    @Story("Delete Cart")
    @Description("DELETE /carts/{id} must return 200 and return the full deleted cart object with products list")
    public void TC_CART_05_deleteCart_return200WithDeletedCartObject() {
        given()
            .spec(requestSpec)
            .pathParam("id", CART_ID)
        .when()
            .delete(Endpoints.CART_BY_ID)
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("id",       equalTo(CART_ID))
            .body("userId",   equalTo(1))
            .body("products", not(empty()));
    }
}
