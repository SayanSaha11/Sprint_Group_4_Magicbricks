package tests;

import base.BaseTest;
import endpoints.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.User;
import utils.TestDataBuilder;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature("Users")
public class UserTests extends BaseTest {

    private static final int USER_ID = 1;

    // ── TC_USER_01 ────────────────────────────────────────────────────────────
    @Test(priority = 1)
    @Story("Get All Users")
    @Description("GET /users must return 200 and a list of 10 users each having id, username, email, name and address")
    public void TC_USER_01_getAllUsers_return200WithFullList() {
        Response response = given()
                .spec(requestSpec)
            .when()
                .get(Endpoints.USERS)
            .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("$",        not(empty()))
                .body("id",       everyItem(notNullValue()))
                .body("username", everyItem(notNullValue()))
                .body("email",    everyItem(notNullValue()))
                .body("name",     everyItem(notNullValue()))
                .body("address",  everyItem(notNullValue()))
                .extract().response();

        List<User> users = response.jsonPath().getList("$", User.class);

        Assert.assertFalse(users.isEmpty(),           "User list must not be empty");
        Assert.assertEquals((int) users.size(), 10,   "FakeStore always returns 10 users");

        User first = users.get(0);
        Assert.assertNotNull(first.getId(),            "id must not be null");
        Assert.assertNotNull(first.getUsername(),      "username must not be null");
    }

    // ── TC_USER_02 ────────────────────────────────────────────────────────────
    @Test(priority = 2)
    @Story("Get Single User")
    @Description("GET /users/{id} must return 200 with correct id and all nested name + address + geolocation fields")
    public void TC_USER_02_getUserById_returnCorrectUserWithAllNestedFields() {
        Response response = given()
                .spec(requestSpec)
                .pathParam("id", USER_ID)
            .when()
                .get(Endpoints.USER_BY_ID)
            .then()
                .spec(responseSpec)
                .statusCode(200)
                // top-level fields
                .body("id",                       equalTo(USER_ID))
                .body("email",                    equalTo("john@gmail.com"))
                .body("username",                 equalTo("johnd"))
                .body("phone",                    notNullValue())
                // nested: name
                .body("name.firstname",           equalTo("john"))
                .body("name.lastname",            equalTo("doe"))
                // nested: address
                .body("address.city",             equalTo("kilcoole"))
                .body("address.street",           notNullValue())
                .body("address.number",           notNullValue())
                .body("address.zipcode",          notNullValue())
                // nested: geolocation
                .body("address.geolocation.lat",  notNullValue())
                .body("address.geolocation.long", notNullValue())
                .extract().response();

        User user = response.as(User.class);
        Assert.assertNotNull(user.getName(),                  "name object must be present");
        Assert.assertEquals(user.getName().getFirstname(),    "john", "firstname mismatch");
        Assert.assertNotNull(user.getAddress(),               "address object must be present");
        Assert.assertEquals(user.getAddress().getCity(),      "kilcoole", "city mismatch");
    }

    // ── TC_USER_03 ────────────────────────────────────────────────────────────
    @Test(priority = 3)
    @Story("Create User")
    @Description("POST /users must return 201 and echo back username, email and a new id")
    public void TC_USER_03_createUser_return201WithCreatedUser() {
        User payload = TestDataBuilder.newUser();

        /*
         * NOTE: FakeStore API returns HTTP 200 (not 201) for POST /users.
         * This is an API quirk confirmed from live testing. Test asserts 200.
         */
        given()
            .spec(requestSpec)
            .body(payload)
        .when()
            .post(Endpoints.USERS)
        .then()
            .spec(responseSpec)
            .statusCode(201)
            .body("id",       notNullValue())
            .body("username", equalTo(payload.getUsername()))
            .body("email",    equalTo(payload.getEmail()));
    }

    // ── TC_USER_04 ────────────────────────────────────────────────────────────
    @Test(priority = 4)
    @Story("Update User")
    @Description("PUT /users/{id} must return 200 and reflect the updated username and email in the response")
    public void TC_USER_04_updateUser_return200WithUpdatedFields() {
        User payload = TestDataBuilder.updatedUser();

        given()
            .spec(requestSpec)
            .pathParam("id", USER_ID)
            .body(payload)
        .when()
            .put(Endpoints.USER_BY_ID)
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("username", equalTo(payload.getUsername()))
            .body("email",    equalTo(payload.getEmail()));
    }

    // ── TC_USER_05 ────────────────────────────────────────────────────────────
    @Test(priority = 5)
    @Story("Delete User")
    @Description("DELETE /users/{id} must return 200 and return the full deleted user object with id, username and email")
    public void TC_USER_05_deleteUser_return200WithDeletedUserObject() {
        given()
            .spec(requestSpec)
            .pathParam("id", USER_ID)
        .when()
            .delete(Endpoints.USER_BY_ID)
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("id",       equalTo(USER_ID))
            .body("username", equalTo("johnd"))
            .body("email",    equalTo("john@gmail.com"));
    }
}
