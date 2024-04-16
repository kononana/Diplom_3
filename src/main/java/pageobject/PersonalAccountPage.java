package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private final By headerConstructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By burgerMainLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    private final By menuProfileLink = By.xpath(".//a[text()='Профиль']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    protected final WebDriver webDriver;

    public PersonalAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitingForProfilePageLoading() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(menuProfileLink));
    }
    @Step("Клик по кнопке Выход на странице Личный Кабинет")
    public void exitButtonClick() {
        webDriver.findElement(exitButton).click();
    }
    @Step("Клик по Логотипу на странице Личный Кабинет")
    public void burgerMainLogoClick() {
        webDriver.findElement(burgerMainLogo).click();
    }
    @Step("Клик по Конструктору в шапке на странице Личный Кабинет")
    public void headerConstructorButtonClick() {
        webDriver.findElement(headerConstructorButton).click();
    }
}