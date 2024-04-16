package pageobject;
import io.qameta.allure.Step;
import org.burger.Endpoints;
import user.RandomUser;
import user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginPage {
    private final By emailInput = By.xpath(".//label[text()='Email']/..//input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/..//input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By loginEnterButton = By.xpath(".//*[text() = 'Вход']");
    protected  final WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitForLoginPageIsLoaded() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loginEnterButton));
    }

    public void insertPassword(String password) {
        webDriver.findElement(passwordInput).sendKeys(password);
    }

    public void insertEmail(String email) {
        webDriver.findElement(emailInput).sendKeys(email);
    }

    public void loginButtonClick() {
        webDriver.findElement(loginButton).click();
    }
    @Step("Ввод данных для логина в поля и нажатие на кнопку Войти")
    public void insertDataAndClickLoginButton(User user) {
        insertEmail(user.getEmail());
        insertPassword(user.getPassword());
        loginButtonClick();
    }
    @Step("Проверка успешной авторизации и перехода на главную страницу")
    public void assertSuccessAuth(User user, MainPage mainPage) {
        waitForLoginPageIsLoaded();
        assertTrue("Авторизация прошла успешно", webDriver.getCurrentUrl().equals(Endpoints.BASE_URL + Endpoints.LOGIN));
        insertDataAndClickLoginButton(user);
        assertTrue("Перешли на главную страницу", mainPage.mainPageLoadedAfterLogin());
    }
}