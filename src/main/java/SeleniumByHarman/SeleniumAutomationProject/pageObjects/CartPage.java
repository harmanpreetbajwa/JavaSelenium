package SeleniumByHarman.SeleniumAutomationProject.pageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;



public class CartPage extends AbstractComponents{
	
	
	WebDriver driver;
	public CartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cart h3")
	List<WebElement> addedItems;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	
	public boolean checkIfItemExistsInCart(String itemName) {
		return addedItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(itemName));
	}
	
	public CheckoutPage openCheckoutPage() {
		checkoutButton.click();
		CheckoutPage checkout = new CheckoutPage(driver);
		return checkout;
		
	}
	
	
	

}
