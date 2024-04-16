import io.restassured.RestAssured;
import org.burger.Endpoints;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;


public abstract class BaseUriTest {

    public static WebDriver webDriver;

    @BeforeClass
    public static void setupURI() {
        RestAssured.baseURI = Endpoints.BASE_URL;
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

