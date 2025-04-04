package SeleniumByHarman.SeleniumAutomationProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	@FindBy(css=".totalRow button")
//	WebElement checkoutButton;
	
	@FindBy(css=".icon-credit-card")
	List<WebElement> creditCartOption;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryInputBox;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	WebElement secondAutoSuggestItem;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	@FindBy(className="hero-primary")
	WebElement msgBox;
	
	By autoSuggestResults = By.cssSelector(".ta-results");
	
//	public void openCheckoutPage() {
//		checkoutButton.click();
//	}
	
	public boolean validateCreditCartOptionExists() {
		return creditCartOption.size() != 0;
	}
	
	public void fillOutShippingInfo(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(countryInputBox, country).build().perform();
		WaitForAnElementToBeVisible(autoSuggestResults);
		secondAutoSuggestItem.click();
		submitButton.click();
	}
	
	public boolean confirmOrderPlacement(String successMessage) {
		String confirmationMsg = msgBox.getText();
		return confirmationMsg.equalsIgnoreCase(successMessage);	
	}
	

}
