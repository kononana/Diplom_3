import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.burger.DriverSetup;
import java.time.Duration;
public class BrowserPrepareTest extends ExternalResource {
    private WebDriver webDriver;
    // для запуска тестов в Firefox использовать значение "firefox, для запуска в ''chrome использовать ''chrome"
    String property = System.getProperty("browser", "chrome" );

    @Override
    protected void before() {
        webDriver = DriverSetup.getDriver(property.toLowerCase());
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    protected void after() {
        webDriver.quit();
    }

}