package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

	private final WebDriver driver;
	
	private int tableIterator = 0;
	
	public MainPage(WebDriver driver){
		this.driver = driver;
		
		// Check that we're on the right page.
        if (!"BlueSource".equals(driver.getTitle())) {
            // Alternatively, we could navigate to the login page, perhaps logging out first
            throw new IllegalStateException("This is not the login page");
        }		
	}
	
	// page element locations
	By addEmployeeButtonLocator = By.className("btn-default");
	By searchBarLocator = By.className("form-control");
	By topFirstNameLocator = By.className("ng-binding");
	
	public MainPage pressAddEmployeeButton(){
    	driver.findElement(addEmployeeButtonLocator).click();	
    	return this;
	}
	public MainPage typeIntoSearchBar(String searchInput){
		driver.findElement(searchBarLocator).sendKeys(searchInput);
		return this;
	}
	
	public String getTopFirstName(){
		return driver.findElements(topFirstNameLocator).get(0).getText();
	}
	public String getNextTableEntryName(){
		tableIterator++;
		return driver.findElements(topFirstNameLocator).get(tableIterator).getText();
	}
}
