package auth;

import api.spotit.Login;
import clientSpotIt.UserClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Before;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class LoginUserTests {
    private String email;
    private String password;
    private Login login;
    private UserClient userClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://spots-it.ru/";
        email = "izumizum@internet.ru";
        password = "skjnjisdla567";
        userClient = new UserClient();
        login = new Login();
    }

    @Test
    @DisplayName("Авторизация пользователя.")
    @Description("Авторизация пользователя под существующем логином")
    public void authorizationTest() {
        login = new Login(email, password);
        Response response = UserClient.checkRequestAuthLogin(login);
        response.then().log().all().assertThat().statusCode(200).body("auth_token", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Авторизация пользователя без активации кода подтверждения.")
    @Description("Авторизация пользователя под существующем логином без активации подтверждающего кода ")
    public void authorizationFailedAfterActivatingCodeTest() {
        String email = "izumyshka@yandex.ru";
        String password = "564Y937TR";
        login = new Login(email, password);
        Response response = UserClient.checkRequestAuthLogin(login);
        response.then().log().all().assertThat().statusCode(400)
                .body("error", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Авторизация несуществующего пользователя.")
    @Description("Авторизация пользователя под несуществующем email")
    public void authorizationFailedTest() {
        String email = "izymizy102030405060@yandex.ru";
        String password = "564Y937TR";
        login = new Login(email, password);
        Response response = UserClient.checkRequestAuthLogin(login);
        response.then().log().all().assertThat().statusCode(400)
                .body("error", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Авторизация без указания пароля.")
    @Description("Поле password пустое")
    public void authorizationPustePolePasswordTest() {
        String email = "izymizy102030405060@yandex.ru";
        String password = "";
        login = new Login(email, password);
        Response response = UserClient.checkRequestAuthLogin(login);
        response.then().log().all().assertThat().statusCode(400)
                .body("password", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Авторизация с указанием некорректного пароля.")
    @Description("Авторизация с указанием некорректного пароля.")
    public void authorizationFailedPasswordTest() {
        String email = "izymizy102030405060@yandex.ru";
        String password = "564Y937TRR";
        login = new Login(email, password);
        Response response = UserClient.checkRequestAuthLogin(login);
        response.then().log().all().assertThat().statusCode(400)
                .body("error", Matchers.notNullValue());
    }

}
