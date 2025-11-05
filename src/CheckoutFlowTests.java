import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Allure;

public class CheckoutFlowTests {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get(Config.BASE_URL);
        Allure.step("Browser opened and navigated to: " + Config.BASE_URL);

    }

    @AfterMethod
    public void CloseBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(testName = "ValidateE2EScenario_AddProductsAndCheckout")
    public void testSelectRandomItemsAndCheckout() {
        // Login step
        Allure.step("Logging in with username: " + Config.USERNAME);
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(Config.USERNAME, Config.PASSWORD);
        Allure.step("Successfully logged in");

        Allure.step("Adding 3 random products to cart");
        productsPage.addRandomProductsToCart(3);
        Allure.step("Products added to cart successfully");

        Allure.step("Navigating to cart");
        Cart cart = productsPage.clickCartIcon();

        Allure.step("Proceeding to checkout");
        cart = cart.clickOnCheckoutButton();

        Allure.step("Filling checkout information");
        cart = cart.fillCheckoutInformation(Config.FIRST_NAME, Config.LAST_NAME, Config.POSTAL_CODE);

        Allure.step("Clicking continue button");
        cart = cart.clickOnContinueButton();

        Allure.step("Completing the order");
        cart = cart.clickOnFinishButton();

        Allure.step("Verifying order completion");
        Assert.assertTrue(cart.isOrderComplete(), "Order completion verification failed");
        Allure.step("Order completed successfully!");
    }
}
