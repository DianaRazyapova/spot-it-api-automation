package rules;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class RulesTests {

    @Test
    @DisplayName("Представление для вывода правил сервиса.")
    public void testGetRules() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/rules/");
        response.then().log().all().assertThat().statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("title", Matchers.notNullValue())
                .body("text", Matchers.notNullValue());
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление для вывода часто задаваемых вопросов по id.")
    @Description("Представление для вывода мероприятий с указанием существующего id")
    public void testGetRulesId() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/rules/1/");
        response.then().log().all().assertThat().statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("title", Matchers.notNullValue())
                .body("text", Matchers.notNullValue());
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление для вывода часто задаваемых вопросов по несуществующего id.")
    @Description("Представление для вывода мероприятий с указанием несуществующего id")
    public void testFailedGetRulesId() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/rules/200/");
        response.then().log().all().assertThat().statusCode(404)
                .body("detail", equalTo("Страница не найдена."));
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление для вывода часто задаваемых вопросов с указанием нулевого id.")
    @Description("Представление для вывода мероприятий с указанием нулевого id")
    public void testNullGetRulesId() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/rules/0/");
        response.then().log().all().assertThat().statusCode(404)
                .body("detail", equalTo("Страница не найдена."));
        System.out.println(response.getBody().asString());
    }
}
