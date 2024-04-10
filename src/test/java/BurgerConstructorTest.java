import org.burger.Endpoints;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.MainPage;
import static org.junit.Assert.assertTrue;
public class BurgerConstructorTest {
    @Rule
    public BrowserPrepareTest prepareTest = new BrowserPrepareTest();
    public MainPage mainPage;

    @Before
    public void setUp() {
        WebDriver webDriver = prepareTest.getWebDriver();
        mainPage = new MainPage(webDriver);
        webDriver.get(Endpoints.BASE_URL);
    }
    @Test
    public void checkSauceMenu() {
        mainPage.clickOnSaucesMenu();
        assertTrue("Выбрана вкладка с соусами", mainPage.saucesMenuSelected());
    }
    @Test
    public void checkBunsMenu() {
        mainPage.clickOnFillingsMenu();
        mainPage.clickOnBunsMenu();
        assertTrue("Выбрана вкладка с булками", mainPage.bunsMenuIsSelected());
    }
    @Test
    public void checkFillingsMenu() {
        mainPage.clickOnFillingsMenu();
        assertTrue("Выбрана вкладка с начинками", mainPage.fillingsMenuIsSelected());
    }

}