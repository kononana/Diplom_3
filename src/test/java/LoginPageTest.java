import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import user.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pageobject.*;
import org.burger.*;

import static org.junit.Assert.assertTrue;

public class LoginPageTest extends BaseUriTest {
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
        user = RandomUser.createRandomUser();
        accessToken = UserAPI.createUser(user).path("accessToken");
    }

    @AfterClass
    public static void afterClass() {
        UserAPI.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти со страницы логина")
    public void checkLoginPage() {
        webDriver.get(Endpoints.BASE_URL + Endpoints.LOGIN);
        loginPage.assertSuccessAuth(user, mainPage);
    }

    @Test
    @DisplayName("Авторизация по кнопке Личный кабинет")
    public void checkLoginFromPersonalAccountButton() {
        webDriver.get(Endpoints.BASE_URL);
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnProfileEnterButton();
        loginPage.assertSuccessAuth(user, mainPage);
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти в аккаунт на Главной")
    public void checkLoginWithEnterButton(){
        webDriver.get(Endpoints.BASE_URL);
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnAccountEnterButton();
        loginPage.assertSuccessAuth(user, mainPage);
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти на странице Регистрации")
    public void checkLoginFromSignUpPage() {
        webDriver.get(Endpoints.BASE_URL + Endpoints.REGISTER);
        SignUpPage signUpPage = new SignUpPage(webDriver);
        signUpPage.clickSignInLink();
        loginPage.assertSuccessAuth(user, mainPage);
    }

    @Test
    @DisplayName("Авторизация через страницу восстановления пароля")
    public void checkLoginFromRecoveryPage() {
        webDriver.get(Endpoints.BASE_URL + Endpoints.PASSWORD_RECOVER);
        PasswordPage passwordPagePage = new PasswordPage(webDriver);
        passwordPagePage.clickSignInLink();
        loginPage.assertSuccessAuth(user, mainPage);
    }



}