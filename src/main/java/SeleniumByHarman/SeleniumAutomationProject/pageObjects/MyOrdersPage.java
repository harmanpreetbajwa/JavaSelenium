package SeleniumByHarman.SeleniumAutomationProject.pageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class MyOrdersPage extends AbstractComponents{
	
	WebDriver driver;
	
	public MyOrdersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=("tr td:nth-child(3)"))
	List<WebElement> orderItems;
	

	public Boolean verifyItemInProductList(String productName) {
		Boolean match = orderItems.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	

}
