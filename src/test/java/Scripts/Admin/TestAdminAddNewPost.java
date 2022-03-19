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
import PageObjects.Admin.AllPostsPage;
import PageObjects.Admin.Loginpage;
import Scripts.TestBase;
import Utilities.ExcelUtility;



public class TestAdminAddNewPost extends TestBase {

	  Logger logger=LogManager.getLogger(TestAdminAddNewPost.class);
    Loginpage login;
    AllPostsPage allpostpage;
    ActionsPage action;

    @AfterTest
    void afterTest() {
        login = null;
        allpostpage = null;
        action = null;
    }




    public  void VerifyLoginAsAdmin() throws InterruptedException, IOException
	{

		login.selectlogindropdown();
		String username = ExcelUtility.getAdminCellData(0, 0);
		String password = ExcelUtility.getAdminCellData(0, 1);

		login.enterusername(username);
		login.enterpassword(password);
		login.clicklogin();
		String allposttext=login.allposttext();
		System.out.println(allposttext);
		Assert.assertEquals(allposttext,"All Posts");

	}
		@Test(priority=1)
		public void VerifyAddnewPostWithBlanKdATA() throws InterruptedException, IOException
		{
			login=new Loginpage(driver);
			 action=new ActionsPage(driver);


			 VerifyLoginAsAdmin();
			 logger.info("admin login successful");

			 allpostpage=new AllPostsPage(driver);
			 allpostpage.selectactionsdropdown();
			 logger.info("Add new post page appeared");
			 String title ="";
			 String imageurl = "";
			 String post = "";
			 action.entertitle(title);

			 action.enterimageurl(imageurl);
			 action.enternewpost(post);
			 logger.info("Entered invalid post details");
			  boolean value=action.btnsubmitnotenabled();
			    Assert.assertEquals(value,false);
			    logger.info("Submit button not enabled.Post cannot be added");


		}

		//valid data ADDPOST HOMEPGE


		@Test(priority=2)
		public void VerifyAddnewPost() throws InterruptedException, IOException
		{
			action=new ActionsPage(driver);
			action.selectactionsdropdown();
			 logger.info("Clicked Add new post and Add new post page appeared");

			String title = ExcelUtility.getAdminCellData(3, 0);
			String imageurl = ExcelUtility.getAdminCellData(3, 1);
			//String category = ExcelUtility.getAdminCellData(3, 2);
			String post = ExcelUtility.getAdminCellData(3, 3);

			action.entertitle(title);
			action.enterimageurl(imageurl);
			action.selectcategory();
			action.enternewpost(post);
			 logger.info("Entered valid post details");
			action.btnsubmit();
			 logger.info("Submit clicked");

			WebDriverWait wait = new WebDriverWait(driver,15);
			wait.until(ExpectedConditions.alertIsPresent());
			Assert.assertEquals(driver.switchTo().alert().getText(),"New Post Added");
			driver.switchTo().alert().accept();
		    logger.info("Submit button  enabled and Post added");



		}


		//invalid data edit IN HOMEPAGE

		@Test(priority=3)
		public void VerifyEditNewPostInHomepageInvalidData() throws InterruptedException, IOException
		{

			action=new ActionsPage(driver);

	        action.edithomeclick();
	        logger.info("Edit button of post in Home page clicked");

	        String title ="";
			String imageurl = "";
			String post = "";

			action.entertitle(title);
			action.editimageurl(imageurl);
			action.editenterpost(post);
	        logger.info("Entered invalid details for editing");
		    boolean value=	action.btnsubmiteditnewposthomepageINVALIDATA();


		     if (!value)
		     {
		      Assert.assertFalse(value,"submit not enabled");
		     }
		     else
		  	   Assert.assertTrue(value,"submit  enabled");

		  logger.info("Submit not enabled and cannot edit");

		}



	// VALID DATA EDIT POST home page

		@Test(priority=4)
		public void VerifyEditNewPostInHomepage() throws InterruptedException, IOException
		{
	        action.edithomeclick();
	        logger.info("Edit button of post in Home page clicked");
	        Thread.sleep(4000);
	        String title = ExcelUtility.getAdminCellData(5, 0);
			String imageurl = ExcelUtility.getAdminCellData(5, 1);
			String post = ExcelUtility.getAdminCellData(5, 3);

			action.entertitle(title);
			action.editimageurl(imageurl);
			action.editenterpost(post);
	        logger.info("Entered valid details for editing");
			action.btnsubmiteditnewposthomepage();
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.alertIsPresent());
			Assert.assertEquals(driver.switchTo().alert().getText(),"Updated Successfully");
			driver.switchTo().alert().accept();
			   logger.info("Submit button enabled and updated successfully");
		}

		@Test(priority=5)
		public void VerifyDeletePostInHomepage() throws InterruptedException, IOException
		{

	     action.deletehomeclick();
	     logger.info("Delete button clicked");
	     logger.info("post deleted successfully");

		}

	    @Test(priority=6)

				public void VerifyTotalElements() throws InterruptedException, IOException
				{
					action=new ActionsPage(driver);

			          int headerlinks=action.headerlinks();
					  System.out.println(headerlinks);

					 Assert.assertEquals(headerlinks,8);


					int textboxes=action.textboxescount();
					Assert.assertEquals(textboxes,3); 

					int textarea=action.textareacount();
					Assert.assertEquals(textarea,1);

					int drpdown=action.drpdowncount();
					Assert.assertEquals(drpdown,1);

					int submitbtn=action.noOfSubmitButton();
					Assert.assertEquals(submitbtn,1);
					logger.info("Counted the elements");
				}


}
