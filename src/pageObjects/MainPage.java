package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

	public final WebDriver driver;
	
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
	By addEmployeeButtonLocator = By.cssSelector("div.btn-group.pull-right > button.btn.btn-default");
	By searchBarLocator = By.className("form-control");
	By topFirstNameLocator = By.className("ng-binding");
	
	public AddEmployeePage pressAddEmployeeButton(){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButtonLocator)).click();;
    	//driver.findElement(addEmployeeButtonLocator).click();	
    	return new AddEmployeePage(this.driver);
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
