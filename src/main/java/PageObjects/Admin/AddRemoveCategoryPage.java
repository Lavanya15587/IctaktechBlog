package PageObjects.Admin;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.pageutility;

public class AddRemoveCategoryPage {

WebDriver driver;

@FindBy(xpath="//a[contains(text(),'Add/Remove category')]")
	 private WebElement addaremovecategorydrpdown;

	    @FindBy(xpath = "//p[contains(text(),'Actions')]")
	    private WebElement actiondrpdown;
	 @FindBy(xpath="//body/app-root[1]/app-category[1]/div[1]/form[1]/input[1]")
	 private WebElement textboxaddcategory;


	 @FindBy(xpath="//button[contains(text(),'Add')]")
	 private WebElement addbtn;


	 @FindBy(xpath="//body/app-root[1]/app-category[1]/div[1]/div[2]/li[1]/button[1]")
	 private WebElement btnremove;


		public AddRemoveCategoryPage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
//enter validcategory in textbox

		public void addCategoryvaliddata()
		{
			 String list = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
			 String res = "";
			    for(var i = 0; i < 5; i++) {
			    	int  rnd = (int) Math.floor(Math.random() * list.length());
			        res = res + list.charAt(rnd);
			    }
				pageutility.waitForElementTobeVisible(driver,textboxaddcategory,50);

textboxaddcategory.clear();
		textboxaddcategory.sendKeys(res);


		}
		//enter invalid category in textbox
		public void addCategoryinvaliddata(String category)
		{
			pageutility.waitForElementTobeVisible(driver,textboxaddcategory,50);

         textboxaddcategory.clear();
		textboxaddcategory.sendKeys(category);


		}
		  //selecting from Actions menu addremovecateogry
	    public void selectactionsdropdownaddremove() throws InterruptedException {

			pageutility.waitForElementTobeVisible(driver,actiondrpdown,50);

	        JavascriptExecutor executor = (JavascriptExecutor) driver;
	        executor.executeScript("arguments[0].click();", actiondrpdown);

			pageutility.waitForElementTobeVisible(driver,addaremovecategorydrpdown,50);
	        Actions act = new Actions(driver);
	        act.click(addaremovecategorydrpdown).perform();

	    }

		//click add button

		public void clickAdd() throws InterruptedException
		{
			pageutility.waitForElementTobeVisible(driver,addbtn,50);

			  Actions act = new Actions(driver);
		        act.click(addbtn).perform();


		}


		//click Remove

		public void clickRemove() throws InterruptedException
		{
			pageutility.waitForElementTobeVisible(driver,btnremove,50);

			  Actions act = new Actions(driver);
		        act.click(btnremove).perform();


		}

		  //click ADD category(INVALID DATA)
	    public boolean btnAddEnabled() throws InterruptedException {

			pageutility.waitForElementTobeVisible(driver,addbtn,50);

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", addbtn);

	        if (addbtn.isEnabled())


			return true;
	        else
	        	return false;


	    }

}
