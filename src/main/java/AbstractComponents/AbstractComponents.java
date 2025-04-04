package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumByHarman.SeleniumAutomationProject.pageObjects.CartPage;
import SeleniumByHarman.SeleniumAutomationProject.pageObjects.MyOrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartIcon;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderIcon;

	public void WaitForAnElementToBeVisible(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void WaitForAnerbElementToBeVisible(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(elem));
	}
	
	public void WaitForAnElementToBeInVisible(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public CartPage openCart() {
		cartIcon.click();
		return new CartPage(driver);
	}
	
	public MyOrdersPage openMyOrders() {
		orderIcon.click();
		return new MyOrdersPage(driver);
	}

}
