import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import user.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page_object.*;
import org.burger.*;

import static org.junit.Assert.assertTrue;

public class LoginPageTest {
    @Rule
    public BrowserPrepareTest prepareTest = new BrowserPrepareTest();
    public static WebDriver webDriver;
    public MainPage mainPage;
    public static String accessToken;
    public static User user;
    public static LoginPage loginPage;
    @Before
    public void setUp() {
        webDriver = prepareTest.getWebDriver();
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        webDriver.get(Endpoints.BASE_URL);
    }

    @BeforeClass
    public static void beforeClass() {
        RestAssured.baseURI = Endpoints.BASE_URL;
        user = RandomUser.createRandomUser();
        accessToken = UserAPI.createUser(user).path("accessToken");
    }

    @AfterClass
    public static void afterClass() {
        UserAPI.deleteUser(accessToken);
    }

    @Test
    public void checkLoginPage() {
        webDriver.get(Endpoints.BASE_URL + Endpoints.LOGIN);
        assertSuccessAuth();
    }

    @Test
    @DisplayName("Авторизация по кнопке Личный кабинет")
    public void checkLoginFromPersonalAccountButton() {
        webDriver.get(Endpoints.BASE_URL);
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnProfileEnterButton();
        assertSuccessAuth();
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти в аккаунт на Главной")
    public void checkLoginWithEnterButton(){
        webDriver.get(Endpoints.BASE_URL);
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnAccountEnterButton();
        assertSuccessAuth();
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти на странице Регистрации")
    public void checkLoginFromSignUpPage() {
        webDriver.get(Endpoints.BASE_URL + Endpoints.REGISTER);
        SignUpPage signUpPage = new SignUpPage(webDriver);
        signUpPage.clickSignInLink();
        assertSuccessAuth();
    }

    @Test
    @DisplayName("Авторизация через страницу восстановления пароля")
    public void checkLoginFromRecoveryPage() {
        webDriver.get(Endpoints.BASE_URL + Endpoints.PASSWORD_RECOVER);
        PasswordPage passwordPagePage = new PasswordPage(webDriver);
        passwordPagePage.clickSignInLink();
        assertSuccessAuth();
    }


    @Step("Главная страница  загружена, кнопка 'Оформить заказ' отображается")
    public static boolean mainPageLoadedAfterLogin() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.waitingForMainPageLoading();
        return mainPage.orderPlaceButtonIsDisplayed();
    }
@Step ("Проверка успешной авторизации и перехода на главную страницу")
    public static void assertSuccessAuth(){
        loginPage.waitForLoginPageIsLoaded();
        assertTrue("Авторизация прошла успешно", webDriver.getCurrentUrl().equals(Endpoints.BASE_URL + Endpoints.LOGIN));
        loginPage.insertDataAndClickLoginButton(user);
        assertTrue("Перешли на главную страницу", mainPageLoadedAfterLogin());
    }
}