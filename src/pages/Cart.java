package pages;

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

    public Cart(WebDriver driver) {
        super(driver);
    }


    public Cart clickOnCheckoutButton() {
        click(checkoutButton);
        return new Cart(driver);
    }

    public Cart clickOnFinishButton(){
        click(finishButton);
        return new Cart(driver);
    }

    public Cart clickOnContinueButton(){
        click(continueButton);
        return  new Cart(driver);
    }


    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    public String getCompleteHeader() {
        return getText(completeHeader);
    }


    public boolean isOrderComplete() {
        return isElementDisplayed(completeHeader) &&
                getCompleteHeader().contains("Thank you");

    }

    public Cart fillCheckoutInformation(String FIRST_NAME, String LAST_NAME, String POSTAL_CODE) {
        sendKeys(firstNameField, FIRST_NAME);
        sendKeys(lastNameField, LAST_NAME);
        sendKeys(postalCodeField, POSTAL_CODE);
        return new Cart(driver);
    }

}

