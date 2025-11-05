import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class ProductsPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public Cart clickCartIcon() {
        click(cartIcon);
        return new Cart(driver);
    }

    public void addProductToCart(int index) {
        if (index >= 0 && index < inventoryItems.size()) {
            WebElement item = inventoryItems.get(index);
            WebElement addToCartButton = item.findElement(org.openqa.selenium.By.className("btn_inventory"));
            click(addToCartButton);
        }
    }

    public void addRandomProductToCart() {
        Random random = new Random();
        int randomIndex = random.nextInt(inventoryItems.size());
        addProductToCart(randomIndex);
    }

    public void addRandomProductsToCart(int count) {
        Random random = new Random();
        int totalProducts = inventoryItems.size();

        if (count > totalProducts) {
            count = totalProducts;
        }

        int addedCount = 0;
        int maxAttempts = totalProducts * 2; // Prevent infinite loop
        int attempts = 0;

        while (addedCount < count && attempts < maxAttempts) {
            int randomIndex = random.nextInt(inventoryItems.size());
            WebElement item = inventoryItems.get(randomIndex);
            WebElement addToCartButton = item.findElement(org.openqa.selenium.By.className("btn_inventory"));

            // Check if button text is "Add to cart" (not already added)
            String buttonText = addToCartButton.getText();
            if (buttonText.equals("Add to cart")) {
                click(addToCartButton);
                addedCount++;
            }
            attempts++;
        }
    }
}

