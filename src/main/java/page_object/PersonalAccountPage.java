package page_object;
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
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(menuProfileLink));
    }

    public void exitButtonClick() {
        webDriver.findElement(exitButton).click();
    }

    public void burgerMainLogoClick() {
        webDriver.findElement(burgerMainLogo).click();
    }

    public void headerConstructorButtonClick() {
        webDriver.findElement(headerConstructorButton).click();
    }
}