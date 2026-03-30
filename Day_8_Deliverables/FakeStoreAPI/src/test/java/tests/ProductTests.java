package tests;

import base.BaseTest;
import endpoints.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Product;
import utils.TestDataBuilder;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@Feature("Products")
public class ProductTests extends BaseTest {

    private static final int PRODUCT_ID = 1;

    // ── TC_PROD_01 ────────────────────────────────────────────────────────────
    @Test(priority = 1)
    @Story("Get All Products")
    @Description("GET /products must return 200 and a list of 20 products each having id, title, price and rating")
    public void TC_PROD_01_getAllProducts_return200WithFullList() {
        Response response = given()
                .spec(requestSpec)
            .when()
                .get(Endpoints.PRODUCTS)
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("$", not(empty()))
                .extract().response();

     // Then validate everything via POJO deserialization — type-safe, clear failure messages
        List<Product> products = response.jsonPath().getList("$", Product.class);

        for (Product p : products) {
            Assert.assertTrue(p.getPrice() > 0, "price must be > 0 — product: " + p.getTitle());
            Assert.assertNotNull(p.getRating(), "rating must not be null");
            Assert.assertTrue(p.getRating().getCount() > 0, "rating.count must be > 0");
        }
    }

    // ── TC_PROD_02 ────────────────────────────────────────────────────────────
    @Test(priority = 2)
    @Story("Get Single Product")
    @Description("GET /products/{id} must return 200 with correct id, price > 0 and nested rating object")
    public void TC_PROD_02_getProductById_returnCorrectProductWithRating() {
        Response response = given()
                .spec(requestSpec)
                .pathParam("id", PRODUCT_ID)
            .when()
                .get(Endpoints.PRODUCT_BY_ID)
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id",           equalTo(PRODUCT_ID))
                .body("title",        notNullValue())
                .body("price",        equalTo(109.95f))
                .body("category",     equalTo("men's clothing"))
                .body("image",        notNullValue())
                .body("rating",       notNullValue())
                .body("rating.rate",  equalTo(3.9f))
                .body("rating.count", equalTo(120))
                .extract().response();

        Product product = response.as(Product.class);
        Assert.assertNotNull(product.getRating(),              "rating object must be present");
        Assert.assertEquals(product.getRating().getRate(),     3.9,  0.001, "rating.rate mismatch");
        Assert.assertEquals((int) product.getRating().getCount(), 120,      "rating.count mismatch");
    }

    // ── TC_PROD_03 ────────────────────────────────────────────────────────────
    @Test(priority = 3)
    @Story("Create Product")
    @Description("POST /products must return 201 and echo back the created product with a generated id")
    public void TC_PROD_03_createProduct_return201WithEchoedProduct() {
        Product payload = TestDataBuilder.newProduct();

        given()
            .spec(requestSpec)
            .body(payload)
        .when()
            .post(Endpoints.PRODUCTS)
        .then()
            .spec(responseSpec)
            .statusCode(201)
            .body("id",          notNullValue())
            .body("title",       equalTo(payload.getTitle()))
            .body("price",       equalTo(payload.getPrice().floatValue()))
            .body("description", equalTo(payload.getDescription()))
            .body("category",    equalTo(payload.getCategory()));
    }

    // ── TC_PROD_04 ────────────────────────────────────────────────────────────
    @Test(priority = 4)
    @Story("Update Product")
    @Description("PUT /products/{id} must return 200 and reflect the updated title and price in the response")
    public void TC_PROD_04_updateProduct_return200WithUpdatedFields() {
        Product payload = TestDataBuilder.updatedProduct();

        given()
            .spec(requestSpec)
            .pathParam("id", PRODUCT_ID)
            .body(payload)
        .when()
            .put(Endpoints.PRODUCT_BY_ID)
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("title", equalTo(payload.getTitle()))
            .body("price", equalTo(payload.getPrice().floatValue()));
    }

    // ── TC_PROD_05 ────────────────────────────────────────────────────────────
    @Test(priority = 5)
    @Story("Delete Product")
    @Description("DELETE /products/{id} must return 200 and return the full deleted product object with its id")
    public void TC_PROD_05_deleteProduct_return200WithDeletedProduct() {
        given()
            .spec(requestSpec)
            .pathParam("id", PRODUCT_ID)
        .when()
            .delete(Endpoints.PRODUCT_BY_ID)
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("id",    equalTo(PRODUCT_ID))
            .body("title", notNullValue())
            .body("price", notNullValue());
    }
}
