// EXECUTION_SCAFFOLD: imports and enclosing class only; original fragments follow.
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.selenium.BrowserWebDriverContainer;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

@Testcontainers
public class SeleniumTest {
@Container static BrowserWebDriverContainer chrome =
new BrowserWebDriverContainer(
"selenium/standalone-chrome:4.51.0-20261010");
@Test void checkTheSiteOut() {
var driver = new RemoteWebDriver(
chrome.getSeleniumAddress(), new ChromeOptions());
try {
driver.get("data:text/html,<h1>University</h1>");
WebElement title = driver.findElement(By.tagName("h1"));
assertEquals("University", title.getText());
} finally { driver.quit(); }
}
}
