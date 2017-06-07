package test;

import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import pageObjects.*;

public class NewTest {
	
	private WebDriver driver;
	
	// test Strings
	private String testURL = "http://bluesourcestaging.herokuapp.com/login";
	private String username = "company.admin";
	private String password = "anything";
	private String firstName = "ajksdksanjkdsfnksd";
	private String lastName = "aaaaaa";
  
	/*// html ids
	private String usernameId = "employee_username";
	private String passwordId = "employee_password";*/
	
	@BeforeTest
	public void beforeTest() {
		driverSetup();
		//driver.get(testURL);
	}

	@AfterTest
	public void afterTest() {
		//driverExit();
	}
	
	@Test
	public void test(){
		LoginPage loginPage = new LoginPage(driver,testURL);
		MainPage mainPage = loginPage.loginAs(username, password);
		//MainPage mainPage = new MainPage(driver);
		AddEmployeePage addEmployeePage = mainPage.pressAddEmployeeButton();
		//AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
		addEmployeePage.fillOutMinimumFormParameters(username, firstName, lastName);
		mainPage = new MainPage(addEmployeePage.driver);
		//mainPage.typeIntoSearchBar(firstName);
		
		assert mainPage.typeIntoSearchBar(firstName).getTopFirstName().equalsIgnoreCase(firstName);
		
		//testLogin();
		//testAddEmployee();
		//assert testIfEmployeeAdded();
	}
	
	
	public void driverSetup(){
		//System.setProperty("webdriver.gecko.driver", "C:\Users\jonathan.diaz\workspace");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.get(testURL);
	}
	public void driverExit(){
		driver.quit();
	}
	
	
	// all my old stuff
	/*
	public void testLogin(){
		WebElement usernameForm = driver.findElement(By.id(usernameId));
		WebElement passwordForm = driver.findElement(By.id(passwordId));
		WebElement loginButton = driver.findElement(By.name("commit"));
		
		usernameForm.sendKeys(username);
		passwordForm.sendKeys(password);
		loginButton.click();
	}
	public void testAddEmployee(){
		WebElement addEmployeeButton = driver.findElement(By.className("btn-default"));
		WebElement usernameForm = driver.findElement(By.id(usernameId));
		WebElement firstNameForm = driver.findElement(By.id("employee_first_name"));
		WebElement lastNameForm = driver.findElement(By.id("employee_last_name"));
		
		// superfluous form sections
		WebElement titleSelect = driver.findElement(By.id("employee_title_id"));
		WebElement roleSelect = driver.findElement(By.id("employee_role"));
		WebElement managerSelect = driver.findElement(By.id("employee_manager_id"));
		WebElement StatusSelect = driver.findElement(By.id("employee_status"));
		WebElement bridgeTimeSelect = driver.findElement(By.id("employee_bridge_time"));
		WebElement locationSelect = driver.findElement(By.id("employee_location"));

		WebElement createEmployeeButton = driver.findElement(By.name("commit"));
		
		addEmployeeButton.click();
		
		// wait for the form to be enabled
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.className("employee_user_name")).isDisplayed();
			}
		});
        
		username = UUID.randomUUID().toString();
		firstName = UUID.randomUUID().toString();
		lastName = UUID.randomUUID().toString();
		usernameForm.sendKeys(username);
		firstNameForm.sendKeys(firstName);
		lastNameForm.sendKeys(lastName);
		
		//new Select(titleSelect).selectByVisibleText("Manager");
		//new Select(roleSelect).selectByVisibleText("Base");
		
		createEmployeeButton.click();
	}
	public boolean testIfEmployeeAdded(){
		
		WebElement searchInput = driver.findElement(By.id("search-bar"));
		searchInput = searchInput.findElement(By.className("form-control"));
		searchInput.sendKeys(firstName);
		
		// wait for the update
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.className("ng-binding")).isDisplayed();
			}
		});
		
		if(driver.findElement(By.className("ng-binding")).getText().equalsIgnoreCase(firstName)){
			driver.findElement(By.className("ng-binding")).click();
			if(driver.findElements(By.tagName("td")).get(1).getText().equalsIgnoreCase(username)){
				return true;
			}
		}
		return false;
	}
	*/
}
