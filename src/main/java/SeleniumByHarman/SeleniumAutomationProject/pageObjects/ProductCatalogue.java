package SeleniumByHarman.SeleniumAutomationProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> cards;
	
	
	public List<WebElement> getProductList() {
		WaitForAnElementToBeVisible(By.cssSelector(".mb-3"));
		return cards;
	}
	
	public WebElement findProductByName(List<WebElement> products, String prodName) {
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
		return prod;
	}
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	public boolean addProductToCart(WebElement prod) {
		if (prod != null) {
			prod.findElement(addToCart).click();
			WaitForAnElementToBeVisible(By.id("toast-container"));
			WaitForAnElementToBeInVisible(By.cssSelector(".ng-animating"));
			return true;
		}
		return false;
	}

}
