package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Allure;
import pages.LoginPage;
import pages.ProductsPage;
import pages.Cart;
import config.Config;

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
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(Config.USERNAME, Config.PASSWORD);
        Allure.step("Logging in with username: " + Config.USERNAME);
        Allure.step("Successfully logged in");
        productsPage.addRandomProductsToCart(3);
        Allure.step("Adding " + 3 + " random products to cart");


        Cart cart = productsPage
                .clickCartIcon()
                .clickOnCheckoutButton()
                .fillCheckoutInformation(Config.FIRST_NAME, Config.LAST_NAME, Config.POSTAL_CODE)
               .clickOnContinueButton()
                .clickOnFinishButton();

        Allure.step("Navigating to cart");
        Allure.step("Proceeding to checkout");
        Allure.step("Navigated to checkout page");
        Allure.step("Checkout information filled and submitted");
        Assert.assertTrue(cart.isOrderComplete(), "Thank you for your order!\n");
        Allure.step("Order completed successfully!");
    }
}

