package subscribe;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SubscribeTests {

    private static final String BASE_URL = "https://spots-it.ru/";
    private static final String API_KEY = "755882797ac802015dde1e030a6438f5e5bed9c0";

    @Test
    @DisplayName("Подписка на новостную ленту авторизированным пользователем")
    public void testSubscribeEndpoint() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .header("Authorization", "token " + API_KEY)
                .contentType(ContentType.JSON)
                .post("/api/v1/subscribe/");
        response.then().log().all().assertThat()
                .statusCode(200).body("message",equalTo("Вы успешно подписались"));
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Подписка на новостную ленту уже подписавшемся пользователем")
    public void testSubscribeEndpointAgain() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .header("Authorization", "token " + API_KEY)
                .contentType(ContentType.JSON)
                .post("/api/v1/subscribe/");
        response.then().log().all().assertThat()
                .statusCode(400).body("error",equalTo("Вы уже подписаны."));
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Отмена подписки на новостную ленту уже подписавшемся пользователем")
    public void testSubscribeUnsubscribe() {
        RestAssured.baseURI = BASE_URL;

        Response response =  RestAssured.given()
                .header("Authorization", "token " + API_KEY)
                .contentType(ContentType.JSON)
                .delete("/api/v1/subscribe/");
        response.then().log().all().assertThat()
                .statusCode(200).body("message",equalTo("Вы успешно отписались"));
        System.out.println(response.getBody().asString());
    }

    @Test
    @DisplayName("Отмена подписки на новостную ленту уже подписавшемся пользователем")
    public void testSubscribeUnsubscribeAgain() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .header("Authorization", "token " + API_KEY)
                .contentType(ContentType.JSON)
                .delete("/api/v1/subscribe/");
                response.then().log().all().assertThat()
                .statusCode(400).body("error",equalTo("Вы не подписаны."));
        System.out.println(response.getBody().asString());
    }
}


