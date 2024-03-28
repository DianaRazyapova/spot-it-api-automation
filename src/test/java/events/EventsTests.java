package events;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;


public class EventsTests {

    @Test
    @DisplayName("Представление для вывода мероприятий.")
    @Description("Представление для вывода мероприятий без id.")
    public void testGetEvents() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/events/");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление для вывода мероприятий с указанием id.")
    @Description("Представление для вывода мероприятий с указанием существующего id")
    public void testGetEventsId() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/events/1/");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление для вывода мероприятий с указанием несуществующего id.")
    @Description("Получение конкретного мероприятия при введении несуществующего id в ручке GET /api/v1/events/{id}/")
    public void testFailedGetEventsId() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/events/150/");
        assertEquals(404, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление для вывода мероприятий с указанием нулевого id.")
    @Description("Представление для вывода мероприятий с указанием нулевого id")
    public void testFailedHullGetEventsId() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/events/0/");
        assertEquals(404, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
}
