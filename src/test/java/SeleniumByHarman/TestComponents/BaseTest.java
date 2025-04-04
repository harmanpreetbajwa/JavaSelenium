package SeleniumByHarman.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumByHarman.SeleniumAutomationProject.pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	
	public LandingPage landingPage ;
	
	
	public WebDriver initializeDriver() throws IOException {
		
		// Properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
				"//src//main//java//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		
		//Chrome Driver
		if (browserName.equalsIgnoreCase("chrome")) 
		{
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		landingPage = new LandingPage(driver);
		return landingPage;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	public List<HashMap<String, String>> convertJsonDataToHashMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		// convert string to hashmap
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,  new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
	}

}
