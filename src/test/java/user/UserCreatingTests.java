package user;
import api.spotit.User;
import clientSpotIt.UserClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;


public class UserCreatingTests {

    private String email;
    private String first_name;

    private String last_name;
    private String password;
    private String re_password;
    private User user;
    private UserClient userClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://spots-it.ru/";
        userClient = new UserClient();
        user = new User();
    }

    @Test
    @DisplayName("Проверка создание уникального пользователя.")
    @Description("Регистрация уникального пользователя c корректными данными.")
    public void checkCreateUserTest() {
        email = "izumka1234567890108@yandex.ru";
        first_name = "Изюмка";
        last_name = "Шпиц";
        password = "trgiаение765";
        re_password = "trgiаение765";
        user = new User(email, first_name, last_name, password, re_password);
        Response response = UserClient.postCreateNewUser(user);
        response.then().log().all().assertThat().statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("email", Matchers.notNullValue())
                .body("first_name", Matchers.notNullValue())
                .body("last_name", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Проверка создание уникального пользователя в поле first_name латинские буквы.")
    @Description("Регистрация уникального пользователя с латинскими буквами.")
    public void checkFirstNameLatinLettersCreateUserTest() {
        String email = RandomStringUtils.randomAlphanumeric(2,15) + "@yandex.ru";
        first_name = "Izumyshka";
        last_name = "Шпиц";
        password = "564Y937TR";
        re_password = "564Y937TR";
        user = new User(email, first_name, last_name, password, re_password);
        Response response = UserClient.postCreateNewUser(user);
        response.then().log().all().assertThat().statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("email", Matchers.notNullValue())
                .body("first_name", Matchers.notNullValue())
                .body("last_name", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Проверка создание уникального пользователя в поле first_name кириллицей.")
    @Description("Регистрация уникального пользователя с кириллицей.")
    public void checkFirstNameСyrillicCreateUserTest() {
        String email = RandomStringUtils.randomAlphanumeric(2,15) + "@yandex.ru";
        first_name = " ЕнгарКотик";
        last_name = "Шотландец";
        password = "564Y937TR";
        re_password = "564Y937TR";
        user = new User(email, first_name, last_name, password, re_password);
        Response response = UserClient.postCreateNewUser(user);
        response.then().log().all().assertThat().statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("email", Matchers.notNullValue())
                .body("first_name", Matchers.notNullValue())
                .body("last_name", Matchers.notNullValue());
    }



    @Test
    @DisplayName("Проверка создание уникального пользователя с использованием спец.символов в поле first_name.")
    @Description("Регистрация уникального пользователя c использованием спец.символов символов в поле first_name.")
    public void checkCreateFirstNameWithSpecialCharactersTest() {
        String email = RandomStringUtils.randomAlphanumeric(2,15) + "@yandex.ru";
        String first_name = "Izum-Izum";
        String last_name = "Izum";
        String password = "564YTebw;";
        String re_password = "564YTebw;";
        user = new User(email, first_name, last_name, password, re_password);
        Response response = UserClient.postCreateNewUser(user);
        response.then().log().all().assertThat().statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("email", Matchers.notNullValue())
                .body("first_name", Matchers.notNullValue())
                .body("last_name", Matchers.notNullValue());;
    }

    @Test
    @DisplayName("Проверка создание уникального пользователя с использованием спец.символов в поле last_name.")
    @Description("Регистрация уникального пользователя c использованием спец.символов символов в поле last_name.")
    public void checkCreateLastNameWithSpecialCharactersTest() {
        String email = RandomStringUtils.randomAlphanumeric(2,15) + "@yandex.ru";
        String first_name = "Izym";
        String last_name = "Izum-Izum";
        String password = "564YTebw;";
        String re_password = "564YTebw;";
        user = new User(email, first_name, last_name, password, re_password);
        Response response = UserClient.postCreateNewUser(user);
        response.then().log().all().assertThat().statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("email", Matchers.notNullValue())
                .body("first_name", Matchers.notNullValue())
                .body("last_name", Matchers.notNullValue());;
    }
    @Test
    @DisplayName("Проверка создания пользователя, который уже зарегистрирован.")
    @Description("Регистрация уже зарегистрированного пользователя.")
    public void checkRegisteredUserTest() {
        user = new User(email, first_name, last_name, password, re_password);
        UserClient.postCreateNewUser(user);
        Response response = UserClient.postCreateNewUser(user);
        response.then().log().all()
                .assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Проверка создание уникального пользователя без домена в email.")
    @Description("Регистрация уникального пользователя без домена.")
    public void checkFailedCreateWithoutDomainUserTest() {
        String email = "tytytytytyty";
        String first_name = "Izym";
        String last_name = "Sp:%№.3646";
        String password = "564Y937TR";
        String re_password = "564Y937TR";
        user = new User(email, first_name, last_name, password, re_password);
        Response response = UserClient.postCreateNewUser(user);
        response.then().log().all().assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Проверка создание уникального пользователя пароль меньше 8 символов.")
    @Description("Регистрация уникального пользователя пароль составляет меньше 8 символов.")
    public void checkFailedPasswordCreateUserTest() {
        String email = RandomStringUtils.randomAlphanumeric(2,15) + "@yandex.ru";
        String first_name = RandomStringUtils.randomAlphanumeric(2,15);
        String last_name = RandomStringUtils.randomAlphanumeric(2,15);
        String password = "564Edfr";
        String re_password = "564Edfr";
        user = new User(email, first_name, last_name, password, re_password);
        Response response = UserClient.postCreateNewUser(user);
        response.then().log().all().assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Проверка создание уникального пользователя пароль больше 128 символов.")
    @Description("Регистрация уникального пользователя пароль составляет больше 128 символов.")
    public void checkFailedBigPasswordCreateUserTest() {
        String email = RandomStringUtils.randomAlphanumeric(2,15) + "@yandex.ru";
        String first_name = RandomStringUtils.randomAlphanumeric(2,15);
        String last_name = RandomStringUtils.randomAlphanumeric(2,15);
        String password = "T4Yfx2u1uNcVDGunfSeuCHwpBu1ylcqNVkUiuRHpHWyV6lXiKssuq3usazJ2bZH7Xylb2c7JLG4hvR4jlgDaH1BYOWwKDsDqzNeIRIvC6xO7VBblOMneyId0gMWEYXoNsE";
        String re_password = "T4Yfx2u1uNcVDGunfSeuCHwpBu1ylcqNVkUiuRHpHWyV6lXiKssuq3usazJ2bZH7Xylb2c7JLG4hvR4jlgDaH1BYOWwKDsDqzNeIRIvC6xO7VBblOMneyId0gMWEYXoNsE";
        user = new User(email, first_name, last_name, password, re_password);
        Response response = UserClient.postCreateNewUser(user);
        response.then().log().all().assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Проверка создания пользователя без email.")
    @Description("Регистрация поверка пользователя с пустым email")
    public void createUserWithoutEmailTest() {
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setPassword(password);
        user.setRe_password(re_password);
        Response response = UserClient.postCreateNewUser(user);
        userClient.checkFailedResponseAuthRegisterNoRequired(response);
    }

    @Test
    @DisplayName("Проверка создания пользователя без имени.")
    @Description("Регистрация поверка пользователя с пустым first_name")
    public void createUserWithoutFirstNameTest() {
        user.setEmail(email);
        user.setLast_name(last_name);
        user.setPassword(password);
        user.setRe_password(re_password);
        Response response = UserClient.postCreateNewUser(user);
        userClient.checkFailedResponseAuthRegisterNoRequired(response);
    }

    @Test
    @DisplayName("Проверка создания пользователя без имени.")
    @Description("Регистрация поверка пользователя с пустым last_name")
    public void createUserWithoutLastNameTest() {
        user.setEmail(email);
        user.setFirst_name(first_name);
        user.setPassword(password);
        user.setRe_password(re_password);
        Response response = UserClient.postCreateNewUser(user);
        userClient.checkFailedResponseAuthRegisterNoRequired(response);
    }

    @Test
    @DisplayName("Проверка создания пользователя без пароля.")
    @Description("Регистрация поверка пользователя с пустым password")
    public void createUserWithoutPasswordTest() {
        user.setEmail(email);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setRe_password(re_password);
        Response response = UserClient.postCreateNewUser(user);
        userClient.checkFailedResponseAuthRegisterNoRequired(response);

    }

    @Test
    @DisplayName("Проверка создания пользователя без пароля.")
    @Description("Регистрация поверка пользователя с пустым re_password")
    public void createUserWithoutRePasswordTest() {
        user.setEmail(email);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setPassword(password);
        Response response = UserClient.postCreateNewUser(user);
        userClient.checkFailedResponseAuthRegisterNoRequired(response);

    }

    @Test
    @DisplayName("Проверка создания пользователя без всех полей.")
    @Description("Регистрация поверка пользователя c пустыми полями email, first_name, last_name, password, re_password.")
    public void createUserWithoutNameAndEmailAndPasswordTest() {
        Response response = UserClient.postCreateNewUser(user);
        userClient.checkFailedResponseAuthRegisterNoRequired(response);
    }
}

