package SeleniumByHarman.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumByHarman.SeleniumAutomationProject.pageObjects.CartPage;
import SeleniumByHarman.SeleniumAutomationProject.pageObjects.CheckoutPage;
import SeleniumByHarman.SeleniumAutomationProject.pageObjects.MyOrdersPage;
import SeleniumByHarman.SeleniumAutomationProject.pageObjects.ProductCatalogue;
import SeleniumByHarman.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{

	static String URL = "https://rahulshettyacademy.com/client/";
	
	//Credentials
	static String USER_NAME = "harmanbajwa@gmail.com";
	static String USER_PASS= "Mypassword@123";
	


//	public static void main(String[] args) throws InterruptedException, IOException {
	
	@Test(dataProvider= "getData", groups= {"PurchaseOrder"})
	public void submitOrder(String USER_NAME, String USER_PASS, String Product) throws InterruptedException, IOException {

		ProductCatalogue catalogue = landingPage.loginApplication(USER_NAME, USER_PASS);
		
		// Add an item in Cart
		List<WebElement> products = catalogue.getProductList();
		WebElement zaraCoat = catalogue.findProductByName(products, Product);
		catalogue.addProductToCart(zaraCoat);
		Thread.sleep(5000);
		CartPage cart = catalogue.openCart();
		
		// check if Item exists in Cart
		boolean itemExists = cart.checkIfItemExistsInCart(Product);
		Assert.assertTrue(itemExists);
		
		
		// Open Checkout Page
		CheckoutPage checkout = cart.openCheckoutPage();
		checkout.validateCreditCartOptionExists();
		checkout.fillOutShippingInfo("India");
		checkout.confirmOrderPlacement("THANKYOU FOR THE ORDER.");
		
//		
//		driver.close();
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue catalogue = landingPage.loginApplication(USER_NAME, USER_PASS);
		MyOrdersPage orders = catalogue.openMyOrders();
		Assert.assertTrue(orders.verifyItemInProductList("ZARA COAT 3"));
	}
	
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {{"harmanbajwa@gmail.com", "Mypassword@123", "ZARA COAT 3"}, {"harmanbajwa@gmail.com", "Mypassword@123", 
			"ADIDAS ORIGINAL"}};
	}
	
	
	@DataProvider
	public Object[][] getDataUsingHashMap() throws IOException{
		// Example of passing data using HashMap
//		HashMap<String, String> data1 = new HashMap<String, String>();
//		data1.put("USER_NAME", "harmanbajwa@gmail.com");
//		data1.put("USER_PASS", "Mypassword@123");
//		data1.put("Product", "ZARA COAT 3");
//		
//		HashMap<String, String> data2 = new HashMap<String, String>();
//		data2.put("USER_NAME", "harmanbajwa@gmail.com");
//		data2.put("USER_PASS", "Mypassword@123");
//		data2.put("Product", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{data1}, {data2}};
		
		// Get data from a JSON file
		List<HashMap<String, String>> mapList = convertJsonDataToHashMap(System.getProperty("user.dir") + "//src//test//java//SeleniumByHarman//data//purchaseOrder.json");
		
		return new Object[][] {{mapList.get(0)}, {mapList.get(1)}};
	}
	
	@Test(dataProvider= "getDataUsingHashMap", groups= {"PurchaseOrder"})
	public void submitOrderUsingHashMapData(HashMap<String, String> data) throws InterruptedException, IOException {

		
		ProductCatalogue catalogue = landingPage.loginApplication(data.get("USER_NAME"), data.get("USER_PASS"));
		
		// Add an item in Cart
		List<WebElement> products = catalogue.getProductList();
		WebElement zaraCoat = catalogue.findProductByName(products, data.get("Product"));
		catalogue.addProductToCart(zaraCoat);
		Thread.sleep(5000);
		CartPage cart = catalogue.openCart();
		
		// check if Item exists in Cart
		boolean itemExists = cart.checkIfItemExistsInCart(data.get("Product"));
		AssertJUnit.assertTrue(itemExists);
		
		
		// Open Checkout Page
		CheckoutPage checkout = cart.openCheckoutPage();
		checkout.validateCreditCartOptionExists();
		checkout.fillOutShippingInfo("India");
		checkout.confirmOrderPlacement("THANKYOU FOR THE ORDER.");

	}

}

