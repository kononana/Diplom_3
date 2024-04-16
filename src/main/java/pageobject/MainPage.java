package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final By orderPlaceButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By accountEnterButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By profileEnterButton = By.xpath(".//p[text()='Личный Кабинет']");

    private final By burgerAssembleTitle = By.xpath(".//*[text()='Соберите бургер']");
    private final By menuBuns = By.xpath(".//span[text()='Булки']/..");
    private final By menuSauces = By.xpath(".//span[text()='Соусы']/..");
    private final By menuFillings = By.xpath(".//span[text()='Начинки']/..");

    protected final WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitingForMainPageLoading() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(burgerAssembleTitle));
    }
    public void clickOnProfileEnterButton() {
        webDriver.findElement(profileEnterButton).click();
    }

    public void clickOnAccountEnterButton() {
        webDriver.findElement(accountEnterButton).click();
    }
    public boolean orderPlaceButtonIsDisplayed() {
        return webDriver.findElement(orderPlaceButton).isDisplayed();
    }
    @Step("Нажатие на раздел Булки в Меню на Главной Странице")
    public void clickOnBunsMenu() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(menuBuns)).click();;
    }
    @Step("Нажатие на раздел Соусы в Меню на Главной Странице")
    public void clickOnSaucesMenu() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
         .until(ExpectedConditions.visibilityOfElementLocated(menuSauces)).click();
    }
    @Step("Нажатие на раздел Начинки в Меню на Главной Странице")
    public void clickOnFillingsMenu() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(menuFillings)).click();
    }
    @Step("Раздел Начинки выбран в Меню на Главной")
    public boolean fillingsMenuIsSelected() {
        return webDriver.findElement(menuFillings).getAttribute("class").contains("current");
    }
    @Step("Раздел Булки выбран в Меню на Главной")
    public boolean bunsMenuIsSelected() {
        return webDriver.findElement(menuBuns).getAttribute("class").contains("current");
    }
    @Step("Раздел Соусы выбран в Меню на Главной")
    public boolean saucesMenuSelected() {
        return webDriver.findElement(menuSauces).getAttribute("class").contains("current");
    }
    @Step("Главная страница загружена, выполнили клик по кнопке'Личный кабинет'")
    public void enterPersonalAccount(){
    waitingForMainPageLoading();
    clickOnProfileEnterButton();
}
    @Step("Главная страница  загружена, кнопка 'Оформить заказ' отображается")
    public  boolean mainPageLoadedAfterLogin() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.waitingForMainPageLoading();
        return mainPage.orderPlaceButtonIsDisplayed();
    }
}