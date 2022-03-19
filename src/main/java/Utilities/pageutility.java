package Utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pageutility {


	 public static WebElement waitForElementTobeVisible(WebDriver driver, WebElement elementToBeLoaded, int Time) {
	        WebDriverWait wait = new WebDriverWait(driver, Time);
	        WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
	        return element;
	    }
	 public static void hitenter(WebDriver driver, WebElement element) {
	        //Retrieve WebElemnt  to perform mouse hover
	    	Actions actions = new Actions(driver);
	    	actions.sendKeys(element,Keys.ENTER);
	    	actions.build().perform();
	    }


}
