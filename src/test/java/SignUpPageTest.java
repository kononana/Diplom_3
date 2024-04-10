import org.burger.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import user.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page_object.*;

public class SignUpPageTest {
    @Rule
    public BrowserPrepareTest prepareTest = new BrowserPrepareTest();
    public WebDriver webDriver;
    public SignUpPage signUpPage;
    public LoginPage loginPage;
    public static String accessToken;

    @Before
    public void setUp() {
        webDriver = prepareTest.getWebDriver();
        RestAssured.baseURI = Endpoints.BASE_URL;
        signUpPage = new SignUpPage(webDriver);
        webDriver.get(Endpoints.BASE_URL + Endpoints.REGISTER);
        signUpPage.waitingForSignUpPageLoading();
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void signUpTest() {
        User user = RandomUser.createRandomUser();
        loginPage = new LoginPage(webDriver);
        signUpPage.InsertDataForSignUp(user);
        signUpPage.clickSignUpButton();
        loginPage.waitForLoginPageIsLoaded();
        Assert.assertEquals(Endpoints.BASE_URL + Endpoints.LOGIN, webDriver.getCurrentUrl());
        Response response = UserAPI.authUser(user);
        Assert.assertEquals("Авторизация прошла успешно", 200, response.statusCode());
        accessToken = response.path("accessToken");
        UserAPI.deleteUser(accessToken);
    }

    @Test
    public void signUpInvalidPasswordTest() {
        User user = RandomUser.createRandomUser();
        user.setPassword("12abc");
        signUpPage.InsertDataForSignUp(user);
        signUpPage.clickSignUpButton();
        Assert.assertTrue("введен невалидный пароль", signUpPage.checkSignUpWrongPasswordError());
        Assert.assertEquals( Endpoints.BASE_URL + Endpoints.REGISTER, webDriver.getCurrentUrl());
        Response response = UserAPI.authUser(user);
        Assert.assertFalse("Авторизация провалилась", response.path("success"));
    }
}