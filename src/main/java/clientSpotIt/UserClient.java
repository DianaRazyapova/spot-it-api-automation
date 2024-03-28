package clientSpotIt;

import api.spotit.Login;
import api.spotit.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {
    @Step("Успешное создание уникального пользователя.")
    public static Response postCreateNewUser(User user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/v1/users/");
    }

    @Step("Неуспешный ответ сервера на регистрацию пользователя.")
    public void checkFailedResponseAuthRegisterNoRequired(Response response) {
        response.then().log().all()
                .assertThat().statusCode(400);

    }

    @Step("Авторизация существующего пользователем.")
    public static Response checkRequestAuthLogin(Login login) {
        return given()
                .log()
                .all()
                .header("Content-type", "application/json")
                .body(login)
                .when()
                .post("/api/v1/auth/token/login/");
    }

}
