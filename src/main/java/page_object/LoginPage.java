package page_object;
import user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final By emailInput = By.xpath(".//label[text()='Email']/..//input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/..//input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By loginEnterButton = By.xpath(".//*[text() = 'Вход']");
    protected final WebDriver webDriver;

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

    public void insertDataAndClickLoginButton(User user) {
        insertEmail(user.getEmail());
        insertPassword(user.getPassword());
        loginButtonClick();
    }
}