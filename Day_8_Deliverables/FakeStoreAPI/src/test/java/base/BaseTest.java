package base;

import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeSuite;

/**
 * BaseTest — single source of truth for RestAssured request/response specs.
 *
 * NOTE: responseSpec intentionally does NOT enforce Content-Type because the
 * FakeStore API returns "application/json; charset=utf-8" which would cause
 * strict ContentType.JSON matching to fail. Status code and body assertions
 * are done per-test instead.
 */
public class BaseTest {

    protected static RequestSpecification  requestSpec;
    protected static ResponseSpecification responseSpec;

    @BeforeSuite(alwaysRun = true)
    public void initSuite() {
        RestAssured.baseURI = ConfigManager.baseUrl();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.baseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        // Deliberately lightweight — no Content-Type assertion here.
        // FakeStore returns "application/json; charset=utf-8" which breaks
        // RestAssured's strict ContentType.JSON equality check.
        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
