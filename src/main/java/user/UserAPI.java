package user;

import org.burger.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {

    @Step("Создание пользователя")
    public static Response createUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(Endpoints.API_REGISTER);
    }

    @Step("Авторизация пользователя")
    public static Response authUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(Endpoints.API_LOGIN);
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(String authToken) {
        String url = Endpoints.API_USER;
        return given()
                .header("Authorization", authToken)
                .delete(url);
    }
}