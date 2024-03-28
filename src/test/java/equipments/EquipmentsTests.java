package equipments;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class EquipmentsTests {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://spots-it.ru/";
    }
    @Test
    @DisplayName("Представление оборудования по локациям с возможностью фильтрации по категориям.")
    @Description("Представление оборудования по локациям с указанием существующего id.")
    public void testGetEquipments() {
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/locations/2/equipments/");
        response.then().log().all().assertThat().statusCode(200)
                .body("count", Matchers.notNullValue())
                .body("next", Matchers.notNullValue())
                .body("previous", Matchers.notNullValue())
                .body("results", Matchers.notNullValue());
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Представление оборудования по локациям с возможностью фильтрации по категориям с указанием несуществующего id.")
    @Description("Представление оборудования по локациям с указанием несуществующего id.")
    public void testGetEquipmentsFailedId() {
        Response response = RestAssured.given()
                .when()
                .get("/api/v1/locations/9223372036854775808/equipments/");
        response.then().log().all().assertThat().statusCode(404)
                .body("detail", equalTo("Страница не найдена."));
        System.out.println(response.getBody().asString());
    }

}
