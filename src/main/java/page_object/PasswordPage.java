package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordPage {
    private final By signInLink = By.xpath(".//a[text()='Войти']");
    protected final WebDriver webDriver;

    public PasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickSignInLink() {
        webDriver.findElement(signInLink).click();
    }
}