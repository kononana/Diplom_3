package pageobject;
import io.qameta.allure.Step;
import user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    private final By nameInputBox = By.xpath(".//label[text()='Имя']/..//input");
    private final By emailInputBox = By.xpath(".//label[text()='Email']/..//input");
    private final By passwordInputBox = By.xpath(".//*[text()='Пароль']/..//input");
    private final By signUpButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By wrongPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private final By signInLink = By.xpath(".//a[text()='Войти']");
    private final By signUpFormTitle = By.xpath(".//main//h2[text()='Регистрация']");

    protected final WebDriver webDriver;

    public SignUpPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @Step("Ввод данных пользователя для успешной Регистрации")
    public void insertDataForSignUp(User user) {
        insertName(user.getName());
        insertEmail(user.getEmail());
        insertPassword(user.getPassword());
    }

    public void waitingForSignUpPageLoading() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(signUpFormTitle));
    }
    public void insertName(String name) {
        webDriver.findElement(nameInputBox).sendKeys(name);
    }

    public void insertEmail(String email) {
        webDriver.findElement(emailInputBox).sendKeys(email);
    }

    public void insertPassword(String password) {
        webDriver.findElement(passwordInputBox).sendKeys(password);
    }
    @Step("Клик по кнопке Зарегестрироваться на странице Регистрации")
    public void clickSignUpButton() {
        webDriver.findElement(signUpButton).click();
    }
    @Step("Клик по кнопке Войти на странице Регистрации")
    public void clickSignInLink() {
        webDriver.findElement(signInLink).click();
    }
    @Step("Проверка отображения ошибке о неккоректном пароле на странице Регистрации")
    public boolean checkSignUpWrongPasswordError() {
        return webDriver.findElement(wrongPasswordMessage).isDisplayed();
    }
}