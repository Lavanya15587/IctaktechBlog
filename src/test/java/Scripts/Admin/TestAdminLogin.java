package Scripts.Admin;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import PageObjects.Admin.AllPostsPage;
import PageObjects.Admin.Loginpage;
import Scripts.TestBase;
import Utilities.ExcelUtility;

public class TestAdminLogin extends TestBase {

	  Logger logger=LogManager.getLogger(TestAdminLogin.class);

    Loginpage login;
    AllPostsPage allPostsPage;
    @AfterTest
    void afterTest() {
        login = null;
        allPostsPage = null;
    }





//INVALID USERNAME AND PASSWORD
	@SuppressWarnings("deprecation")
	@Test(priority=1)
	public void VerifyInvalidUsernamePassword() throws InterruptedException, IOException
	{
		login=new Loginpage(driver);
		login.selectlogindropdown();

		String username = ExcelUtility.getAdminCellData(1, 0);
		String password = ExcelUtility.getAdminCellData(1, 1);

		login.enterusername(username);
		logger.info("Invalid username entered");
		login.enterpassword(password);
		logger.info("Invalid password entered");

		login.clicklogin();
		logger.info("Login button clicked");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(),"User not found");
		driver.switchTo().alert().accept();
		logger.info("User not found");


	}

	//INVALID USERNAME VALID PASSWORD
	@Test(priority=2)
	public void VerifyInvalidUsernameValidPassword() throws InterruptedException, IOException
	{
		login=new Loginpage(driver);
		login.selectlogindropdown();

		String username = ExcelUtility.getAdminCellData(7, 0);
		String password = ExcelUtility.getAdminCellData(7, 1);



		login.enterusername(username);
		logger.info("Invalid username entered");
		login.enterpassword(password);
		logger.info("valid password entered");
		login.clicklogin();
		logger.info("Login button clicked");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(),"User not found");
		driver.switchTo().alert().accept();
		logger.info("User not found");


	}

	//VALID USERNAME INVALID PASWWORD

		@Test(priority=3)
		public void VerifyValidUsernameInvalidPassword() throws InterruptedException, IOException
		{
			login=new Loginpage(driver);
			login.selectlogindropdown();

			String username = ExcelUtility.getAdminCellData(6, 0);
			String password = ExcelUtility.getAdminCellData(6, 1);

			login.enterusername(username);
			logger.info("valid username entered");
			login.enterpassword(password);
			logger.info("Invalid password entered");
			login.clicklogin();
			logger.info("Login clicked");
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.alertIsPresent());
			Assert.assertEquals(driver.switchTo().alert().getText(),"User not found");
			driver.switchTo().alert().accept();
			logger.info("User not found");

		}

		//BLANK USERNAME BLANK PASWWORD

			@Test(priority=4)
			public void VerifyBlankUsernamePassword() throws InterruptedException, IOException
			{
				login=new Loginpage(driver);
				login.selectlogindropdown();

				String username = "";
				String password ="";

				login.enterusername(username);
				logger.info("valid username entered");
				login.enterpassword(password);
				logger.info("Invalid password entered");
				login.clicklogin();
				logger.info("Login clicked");


			}


		    @Test(priority=5)
			public  void VerifyLoginAsAdmin() throws InterruptedException, IOException
			{
				login=new Loginpage(driver);

				login.selectlogindropdown();
				String username = ExcelUtility.getAdminCellData(0, 0);
				String password = ExcelUtility.getAdminCellData(0, 1);

				login.enterusername(username);
				logger.info("valid username entered");
				login.enterpassword(password);
				logger.info("valid password entered");
				login.clicklogin();
				logger.info("Login clicked");

				String allposttext=login.allposttext();
				Assert.assertEquals(allposttext,"All Posts");
				logger.info("Admin login successful and All posts page opened");
				VerifyLogout();


			}


			public void VerifyLogout() throws InterruptedException, IOException
			{
				login=new Loginpage(driver);
				login.logout();
				logger.info("Logged out");
			}

		    @Test(priority=6)

			public void VerifyTotalElementsLoginPage() throws InterruptedException, IOException
			{
				login=new Loginpage(driver);
				
				  int headerlinks=login.headerlinks();
				  System.out.println(headerlinks);

				   Assert.assertEquals(headerlinks,5);
					int submitbtn=login.noOfSubmitButton();
					Assert.assertEquals(submitbtn,1);

				int textboxes=login.TextboxesCount();
				Assert.assertEquals(textboxes,2);

				logger.info("Counted the elements in loginpage");
			}


}

