package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordPage {
    private final By signInLink = By.xpath(".//a[text()='Войти']");
    protected final WebDriver webDriver;

    public PasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @Step("Клик по ссылке Востановить пароль на странице авторизации")
    public void clickSignInLink() {
        webDriver.findElement(signInLink).click();
    }
}