import io.qameta.allure.junit4.DisplayName;
import org.burger.*;
import io.restassured.response.Response;
import user.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pageobject.*;

public class SignUpPageTest extends BaseUriTest {
    @Rule
    public BrowserPrepareTest prepareTest = new BrowserPrepareTest();
    public WebDriver webDriver;
    public SignUpPage signUpPage;
    public LoginPage loginPage;
    public static String accessToken;

    @Before
    public void setUp() {
        webDriver = prepareTest.getWebDriver();
        signUpPage = new SignUpPage(webDriver);
        webDriver.get(Endpoints.BASE_URL + Endpoints.REGISTER);
        signUpPage.waitingForSignUpPageLoading();
    }

    @Test
    @DisplayName("Проверка успешности регистрации нового пользователя")
    public void signUpTest() {
        User user = RandomUser.createRandomUser();
        loginPage = new LoginPage(webDriver);
        signUpPage.insertDataForSignUp(user);
        signUpPage.clickSignUpButton();
        loginPage.waitForLoginPageIsLoaded();
        Assert.assertEquals(Endpoints.BASE_URL + Endpoints.LOGIN, webDriver.getCurrentUrl());
        Response response = UserAPI.authUser(user);
        Assert.assertEquals("Авторизация прошла успешно", 200, response.statusCode());
        accessToken = response.path("accessToken");
        UserAPI.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка получения ошибки для некорректного пароля при регистрации")
    public void signUpInvalidPasswordTest() {
        User user = RandomUser.createRandomUser();
        user.setPassword("12abc");
        signUpPage.insertDataForSignUp(user);
        signUpPage.clickSignUpButton();
        Assert.assertTrue("введен невалидный пароль", signUpPage.checkSignUpWrongPasswordError());
        Assert.assertEquals( Endpoints.BASE_URL + Endpoints.REGISTER, webDriver.getCurrentUrl());
        Response response = UserAPI.authUser(user);
        Assert.assertFalse("Авторизация провалилась", response.path("success"));
    }
}