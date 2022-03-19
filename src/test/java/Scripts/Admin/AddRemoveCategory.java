package Scripts.Admin;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import PageObjects.Admin.ActionsPage;
import PageObjects.Admin.AddRemoveCategoryPage;
import PageObjects.Admin.AllPostsPage;
import PageObjects.Admin.Loginpage;
import Scripts.TestBase;
import Utilities.ExcelUtility;

public class AddRemoveCategory extends TestBase{
	  Logger logger=LogManager.getLogger(AddRemoveCategory.class);


	 Loginpage login;
	    AllPostsPage allPostsPage;
	    ActionsPage action;
       AddRemoveCategoryPage addremove;
	    @AfterTest
	    void afterTest() {
	        login = null;
	        allPostsPage = null;
	        action=null;
	       addremove=null;
	    }


	public  void VerifyLoginAsAdmin() throws InterruptedException, IOException
	{
		login=new Loginpage(driver);
		Thread.sleep(4000);

		login.selectlogindropdown();
		String username = ExcelUtility.getAdminCellData(0, 0);
		String password = ExcelUtility.getAdminCellData(0, 1);

        login.enterusername(username);
		login.enterpassword(password);
		login.clicklogin();
		String allposttext=login.allposttext();
		System.out.println(allposttext);
		Assert.assertEquals(allposttext,"All Posts");
		logger.info("logged in as admin");

	}
	@Test(priority=1)
	public void AddCategoryValidData() throws InterruptedException, IOException
	{

		VerifyLoginAsAdmin();
		action=new ActionsPage(driver);
		action.addremoveselect();
		logger.info("clicked  Actions menu and clickedpost Add/Remove category ");
		addremove=new AddRemoveCategoryPage(driver);
		addremove.addCategoryvaliddata();
		logger.info("Entered valid category name ");
		addremove.clickAdd();
		logger.info("clicked  Add button");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(),"category added successfully");
		driver.switchTo().alert().accept();
		logger.info("category added successfully");

	}

	@Test(priority=2)
	public void AddCategoryInVaildData() throws InterruptedException, IOException
	{

		 action=new ActionsPage(driver);
		 action.addremoveselect();
			logger.info("clicked  Actions menu and clickedpost Add/Remove category ");
		 addremove=new AddRemoveCategoryPage(driver);
		 String categoryinvalid = ExcelUtility.getAdminCellData(8, 1);
		addremove.addCategoryinvaliddata(categoryinvalid);
		logger.info("Entered category in lowercase");
		boolean value=addremove.btnAddEnabled();

		   Assert.assertEquals(value, false);
			logger.info("Add button is disabled ");

	}


	@Test(priority=3)
	public void AddCategoryAlreadyExists() throws InterruptedException, IOException
	{
		String categoryexists = ExcelUtility.getAdminCellData(8,0);
		addremove.addCategoryinvaliddata(categoryexists);
		logger.info("Entered category already existing");
		addremove.clickAdd();
		logger.info("clicked  Add button ");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(),"Category already exist");
		driver.switchTo().alert().accept();
		logger.info("Category already exist-message displayed");

	}

	@Test(priority=4)
	public void RemoveCategory() throws InterruptedException, IOException
	{

		addremove.clickRemove();
		logger.info("Remove button clicked");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(),"category deleted successfully");
		driver.switchTo().alert().accept();
		logger.info("category deleted successfully-message displayed");

	}
}
