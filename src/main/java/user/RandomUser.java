package user;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class RandomUser {

    public static Faker faker = new Faker();

    @Step("Создание рандомного юзера")
    public static User createRandomUser() {
        return new User(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                faker.internet().password());
    }
}