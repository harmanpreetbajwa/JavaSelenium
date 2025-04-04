package SeleniumByHarman.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import SeleniumByHarman.SeleniumAutomationProject.pageObjects.LandingPage;


public class StandAloneTest {
	
	static String URL = "https://rahulshettyacademy.com/client/";
	
	// Locators
	static String USER_EMAIL = "userEmail";
	static String PASSWORD = "userPassword";
	static String ID = "login";
	
	//Credentials
	static String USER_NAME = "harmanbajwa@gmail.com";
	static String USER_PASS= "Mypassword@123";
	
	
	
	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(URL);
		
//		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id(USER_EMAIL)).sendKeys(USER_NAME);
		driver.findElement(By.id(PASSWORD)).sendKeys(USER_PASS);
		driver.findElement(By.id(ID)).click();
		Thread.sleep(5000);
		
		
		List<WebElement> cards = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement zaraCoat = cards.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	
//		WebElement zaraCoat = null;
		
//		for (WebElement card: cards) {
//			if (card.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"));
//				zaraCoat = card;
//				break;
//		}
		
		zaraCoat.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		// cart page
		
		WebElement cart = driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']"));
		cart.click();
		List <WebElement> selectedItems =  driver.findElements(By.cssSelector(".cart h3"));
		boolean isPresent = selectedItems.stream().anyMatch(item-> item.getText().equalsIgnoreCase("ZARA COAT 3"));
		System.out.println("Is Element Present: " + isPresent);
		Assert.assertTrue(isPresent);
		//h1[contains(text(),'My Cart')]
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		boolean creditCardButton = driver.findElements(By.cssSelector(".icon-credit-card")).size() != 0;
		System.out.println("Is Credit card button there: " + creditCardButton);
		
		WebElement country = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));

//		country.sendKeys("India");		
		
//		WebElement india = autoSuggestOptions.stream().filter(elem -> elem.findElement(By.cssSelector(".ta-item")).getText().equals(" India")).findFirst().orElse(null);
//		india.click();
		
		Actions a = new Actions(driver);
		a.sendKeys(country, "India").build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmation = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
	}

}
