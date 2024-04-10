import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import user.*;
import page_object.*;
import org.burger.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PersonalAccountTest {
    @Rule
    public BrowserPrepareTest prepareTest = new BrowserPrepareTest();
    public MainPage mainPage;
    public static String accessToken, refreshToken;
    public static User user;
    public WebDriver webDriver;
    public PersonalAccountPage personalAccountPage;
    public LoginPage loginPage;

    @Before
    public void setUp() {
        webDriver = prepareTest.getWebDriver();
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        personalAccountPage = new PersonalAccountPage(webDriver);
        webDriver.get(Endpoints.BASE_URL);
        LocalStorage localStorage = ((WebStorage) webDriver).getLocalStorage();
        localStorage.setItem("accessToken", accessToken);
        localStorage.setItem("refreshToken", refreshToken);
    }

    @BeforeClass
    public static void beforeClass() {
        RestAssured.baseURI = Endpoints.BASE_URL;
        user = RandomUser.createRandomUser();
        Response response = UserAPI.createUser(user);
        accessToken = response.path("accessToken");
        refreshToken = response.path("refreshToken");
    }

    @AfterClass
    public static void afterClass() {
        UserAPI.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта")
    public void checkProfileExitButton() {
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnProfileEnterButton();
        personalAccountPage.waitingForProfilePageLoading();
        personalAccountPage.exitButtonClick();
        loginPage.waitForLoginPageIsLoaded();
        assertEquals("Зашли на страницу логина", Endpoints.BASE_URL + Endpoints.LOGIN, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка перехода на Главную из Акаунта (клик на логотип)")
    public void checkMoveToMainPageAfterLogin() {
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnProfileEnterButton();
        personalAccountPage.waitingForProfilePageLoading();
        personalAccountPage.burgerMainLogoClick();
        mainPage.waitingForMainPageLoading();
        assertEquals("Перешли на главную страницу после авторизации", Endpoints.BASE_URL + "/", webDriver.getCurrentUrl());
    }


    @Test
    @DisplayName("Проверка перехода на Главную из Акаунта (клик на Конструктор)")
    public void checkMoveFromProfileClickingOnLogoButton() {
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnProfileEnterButton();
        personalAccountPage.waitingForProfilePageLoading();
        personalAccountPage.headerConstructorButtonClick();
        mainPage.waitingForMainPageLoading();
        assertEquals("Перешли на главную страницу по нажатию на логотип", Endpoints.BASE_URL + "/", webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в личный кабинет пользователя с главной страницы")
    public void checkFromMainToPersonalPassage() {
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnProfileEnterButton();
        personalAccountPage.waitingForProfilePageLoading();
        assertEquals("Перешли в личный кабинет по нажатию на кнопку 'Личный Кабинет' на главной странице", Endpoints.BASE_URL + Endpoints.PROFILE, webDriver.getCurrentUrl());
    }

}