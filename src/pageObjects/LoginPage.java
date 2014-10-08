package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private final WebDriver driver;
	
	public LoginPage(WebDriver driver, String URL){
		this.driver = driver;
		driver.get(URL);
		
		// Check that we're on the right page.
        if (!"BlueSource".equals(driver.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the login page");
        }		
	}
	
	// page element locations
	By usernameFormLocator = By.id("employee_username");
    By passwordFormLocator = By.id("employee_password");
    By loginButtonLocator = By.name("commit");
    
    public MainPage loginAs(String username, String password){
    	typeUsername(username);
    	typePassword(password);
    	return pressLoginButton();
    }
    
    public LoginPage typeUsername(String username){
    	driver.findElement(usernameFormLocator).sendKeys(username);
    	return this;
    }
    public LoginPage typePassword(String password){
    	driver.findElement(passwordFormLocator).sendKeys(password);   	
    	return this;
    }
    public MainPage pressLoginButton(){
    	driver.findElement(loginButtonLocator).click();	
    	return new MainPage(driver);
    }
}
