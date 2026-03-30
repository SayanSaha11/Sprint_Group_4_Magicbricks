package tests;

import base.BaseTest;
import config.ConfigManager;
import endpoints.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


@Feature("Auth")
public class AuthTests extends BaseTest {

    // ── TC_AUTH_01 ────────────────────────────────────────────────────────────
    @Test(priority = 1)
    @Story("Login")
    @Description("POST /auth/login with valid credentials must return 200 and a token field")
    public void TC_AUTH_01_validCredentials_return200WithToken() {
        given()
            .spec(requestSpec)
            .body(TestDataBuilder.validLoginRequest(
                    ConfigManager.defaultUsername(),
                    ConfigManager.defaultPassword()))
        .when()
            .post(Endpoints.LOGIN)
        .then()
            .spec(responseSpec)
            .statusCode(201)
            .body("token", notNullValue());
    }

    // ── TC_AUTH_02 ────────────────────────────────────────────────────────────
    @Test(priority = 2)
    @Story("Login")
    @Description("Token returned on login must be a non-blank string")
    public void TC_AUTH_02_loginResponse_tokenIsNonBlankString() {
        Response response = given()
                .spec(requestSpec)
                .body(TestDataBuilder.validLoginRequest(
                        ConfigManager.defaultUsername(),
                        ConfigManager.defaultPassword()))
            .when()
                .post(Endpoints.LOGIN)
            .then()
                .spec(responseSpec)
                .statusCode(201)
                .extract().response();

        String token = response.jsonPath().getString("token");
        Assert.assertNotNull(token,         "Token must not be null");
        Assert.assertFalse(token.isBlank(), "Token must not be blank");
    }
}
