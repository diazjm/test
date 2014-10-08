package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeePage {
	
	public final WebDriver driver;
	
	public AddEmployeePage(WebDriver driver){
		this.driver = driver;
		
		// Check that we're on the right page.
        if (!"BlueSource".equals(driver.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the login page");
        }		
	}

	// page element locations
	By usernameFormLocator = By.id("employee_username");
	By firstNameFormLocator = By.id("employee_first_name");
	By lastNameFormLocator = By.id("employee_last_name");
	By createEmployeeButtonLocator = By.name("commit");

	// superfluous form sections
	/*
	WebElement titleSelect = driver.findElement(By.id("employee_title_id"));
	WebElement roleSelect = driver.findElement(By.id("employee_role"));
	WebElement managerSelect = driver.findElement(By.id("employee_manager_id"));
	WebElement StatusSelect = driver.findElement(By.id("employee_status"));
	WebElement bridgeTimeSelect = driver.findElement(By.id("employee_bridge_time"));
	WebElement locationSelect = driver.findElement(By.id("employee_location"));
	*/
	
	public AddEmployeePage fillOutMinimumFormParameters(String username, String firstName, String lastName){
		typeUsername(username);
		typeFirstName(firstName);
		typeLastName(lastName);
		return pressCreateEmployeeButton();
	}

	public AddEmployeePage typeUsername(String username){
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(usernameFormLocator).isDisplayed();
			}
		});
		
		driver.findElement(usernameFormLocator).sendKeys(username);
		return this;
	}
	public AddEmployeePage typeFirstName(String firstName){
		driver.findElement(firstNameFormLocator).sendKeys(firstName);
		return this;
	}
	public AddEmployeePage typeLastName(String lastName){
		driver.findElement(lastNameFormLocator).sendKeys(lastName);
		return this; 
	}
	public AddEmployeePage pressCreateEmployeeButton(){
		driver.findElement(createEmployeeButtonLocator).click();
		return this;
	}
}
