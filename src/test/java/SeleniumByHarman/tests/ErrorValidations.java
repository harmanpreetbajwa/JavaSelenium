package SeleniumByHarman.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumByHarman.SeleniumAutomationProject.pageObjects.CartPage;
import SeleniumByHarman.SeleniumAutomationProject.pageObjects.ProductCatalogue;
import SeleniumByHarman.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest{

	static String URL = "https://rahulshettyacademy.com/client/";
	
	//Incorrect Credentials
	static String WRONG_USER_NAME = "harman@yahoo.com";
	static String WRONG_USER_PASS= "Mypassword";
	//Credentials
	static String USER_NAME = "harmanbajwa@gmail.com";
	static String USER_PASS= "Mypassword@123";
	
	
	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() throws InterruptedException, IOException {

		
		// Login to application
		landingPage.loginApplication(WRONG_USER_NAME, WRONG_USER_PASS);
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or ");
		
	}
	
	@Test(groups= {"ErrorHandling"})
	public void cartErrorValidation() throws InterruptedException, IOException {

		
		ProductCatalogue catalogue = landingPage.loginApplication("harman01@gmail.com", USER_PASS);
		
		// Add an item in Cart
		List<WebElement> products = catalogue.getProductList();
		WebElement zaraCoat = catalogue.findProductByName(products, "ZARA COAT 3");
		catalogue.addProductToCart(zaraCoat);
		Thread.sleep(5000);
		CartPage cart = catalogue.openCart();
		
		// check if Item exists in Cart
		boolean itemExists = cart.checkIfItemExistsInCart("ZARA COAT 33");
		Assert.assertFalse(itemExists);

}
	}

