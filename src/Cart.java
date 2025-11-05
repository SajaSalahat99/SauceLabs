import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class Cart extends BasePage {
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    public Cart(WebDriver driver) {
        super(driver);
    }

    public Cart clickOnCheckoutButton() {
        click(checkoutButton);
        return new Cart(driver);
    }

    public Cart clickOnFinishButton() {
        click(finishButton);
        return new Cart(driver);
    }

    public Cart clickOnContinueButton() {
        click(continueButton);
        return new Cart(driver);
    }

    public String getCompleteHeader() {
        return getText(completeHeader);
    }

    public boolean isOrderComplete() {
        return isElementDisplayed(completeHeader) &&
                getCompleteHeader().contains("Thank you");
    }

    public Cart fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(postalCodeField, postalCode);
        return new Cart(driver);
    }
}

