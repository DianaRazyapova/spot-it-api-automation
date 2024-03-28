package locations;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class LocationsTests {

    @Test
    @DisplayName("Представление подробной информации о локациях с возможностью фильтрации по названию, категориям, метро и избранному.")
    public void testGetLocations() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/locations/");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление подробной информации о локациях с возможностью фильтрации по названию, категориям, метро и избранному.")
    @Description("Представление для вывода информации с указанием существующего id.")
    public void testGetLocationsId() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/locations/2/");
        response.then().log().all().assertThat().statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.notNullValue())
                .body("get_full_address_str", Matchers.notNullValue())
                .body("metro", Matchers.notNullValue())
                .body("open_time", Matchers.notNullValue())
                .body("close_time", Matchers.notNullValue())
                .body("low_price", Matchers.notNullValue())
                .body("main_photo", Matchers.notNullValue())
                .body("extra_photo", Matchers.notNullValue())
                .body("short_annotation", Matchers.notNullValue())
                .body("description", Matchers.notNullValue())
                .body("is_favorited", Matchers.notNullValue())
                .body("count_workspace", Matchers.notNullValue())
                .body("count_meeting_room", Matchers.notNullValue())
                .body("coordinates", Matchers.notNullValue())
                .body("days_open", Matchers.notNullValue());
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление подробной информации о локациях с возможностью фильтрации по названию, категориям, метро и избранному по несуществующему id.")
    @Description("Представление для вывода информации с указанием несуществующего id.")
    public void testFailedGetLocationsId() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/locations/150/");
        response.then().log().all().assertThat().statusCode(404)
                .body("detail", equalTo("Страница не найдена."));
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление подробной информации о локациях с возможностью фильтрации по названию, категориям, метро и избранному с указанием нулевого id.")
    @Description("Представление для вывода информации с указанием нулевого id.")
    public void testFailedNullGetLocationsId() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/locations/0/");
        response.then().log().all().assertThat().statusCode(404)
                .body("detail", equalTo("Страница не найдена."));
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление краткой информации о локациях с возможностью фильтрации по названию, категориям, метро и избранному и поиску по названию.")
    public void testGetLocationsShortLocations() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/short_locations/");
        response.then().log().all().assertThat().statusCode(200)
                .body("count", Matchers.notNullValue())
                .body("next", Matchers.notNullValue())
                .body("previous", Matchers.notNullValue())
                .body("results", Matchers.notNullValue());
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление краткой информации о локациях для отображения на карте.")
    public void testGetLocationsMapLocations() {
        RestAssured.baseURI = "https://spots-it.ru/";
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/map_locations/");
        response.then().log().all().assertThat().statusCode(200)
                .body("count", Matchers.notNullValue())
                .body("next", Matchers.notNullValue())
                .body("previous", Matchers.notNullValue())
                .body("results", Matchers.notNullValue());
        System.out.println(response.getBody().asString());
    }

}
