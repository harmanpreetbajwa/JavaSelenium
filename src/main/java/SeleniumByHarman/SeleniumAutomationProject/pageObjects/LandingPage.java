package SeleniumByHarman.SeleniumAutomationProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// WebElement userEmail = driver.findElement(By.id(USER_EMAIL));
	
	// PageFactory 
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(className="ng-trigger-flyInOut")
	WebElement incorrectCredToast;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		WaitForAnerbElementToBeVisible(incorrectCredToast);
		return incorrectCredToast.getText();
	}

}
