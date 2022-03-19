package Scripts.Admin;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import PageObjects.Admin.ActionsPage;
import PageObjects.Admin.AdminMyPostsPage;
import PageObjects.Admin.AllPostsPage;
import PageObjects.Admin.Loginpage;
import Scripts.TestBase;
import Utilities.ExcelUtility;

public class TestAdminMyPostPage extends TestBase {


	  Logger logger=LogManager.getLogger(TestAdminMyPostPage .class);
    Loginpage login;
    ActionsPage action;
    AllPostsPage allpostpage;
    AdminMyPostsPage mypostpage;
    @AfterTest
    void afterTest() {
        login = null;
        action=null;
        allpostpage = null;
        mypostpage=null;
    }


  //ADD POST

    public  void VerifyLoginAsAdmin() throws InterruptedException, IOException
 	{
 		//driver.navigate().refresh();

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

  public void VerifyAddnewPost() throws InterruptedException, IOException
  {
  	action=new ActionsPage(driver);
  	action.selectactionsdropdown();

  	String title = ExcelUtility.getAdminCellData(3, 0);
  	String imageurl = ExcelUtility.getAdminCellData(3, 1);
  	//String category = ExcelUtility.getCellData(3, 2);
  	String post = ExcelUtility.getAdminCellData(3, 3);


  	action.entertitle(title);
  	action.enterimageurl(imageurl);
  	action.selectcategory();
  	action.enternewpost(post);
  	action.btnsubmit();
  	Assert.assertEquals(driver.switchTo().alert().getText(),"New Post Added");
  	driver.switchTo().alert().accept();



  }

     //ADD EDIT DELETE IN MYPOSTS PAGE


  	//valid data

  	@Test(priority=1)
  	public void VerifyEditNewPostInMyPostpage() throws InterruptedException, IOException
  	{
  		login=new Loginpage(driver);
  		 action=new ActionsPage(driver);

  		VerifyLoginAsAdmin();
  		logger.info("admin login successfull");

  		//VerifyAddnewPost();
  

  		mypostpage=new AdminMyPostsPage(driver);
  		mypostpage.mypostlinkclick();

  		logger.info("My post page opened");
  		mypostpage.editmypostclick();


  		logger.info("Edit button of my post page clicked and edit post page opened");
         String title = ExcelUtility.getAdminCellData(5, 0);
  		String imageurl = ExcelUtility.getAdminCellData(5, 1);
  		String post = ExcelUtility.getAdminCellData(5, 3);
         mypostpage.entertitle(title);


         mypostpage.editallpostimageurl(imageurl);
         mypostpage.editentermypost(post);

   		logger.info("Entered valid details for editing");
         mypostpage.btnsubmiteditmypostpage();

   		logger.info("Submit clicked");
  		Assert.assertEquals(driver.switchTo().alert().getText(),"Updated Successfully");
  		driver.switchTo().alert().accept();
  		logger.info("Updated Successfully");
  	}


  //invalid data

  		@Test(priority=2)
  		public void VerifyEditNewPostInMyPostpageINVALIDATA() throws InterruptedException, IOException
  		{
  			mypostpage=new AdminMyPostsPage(driver);
  			//VerifyAddnewPost();
  			mypostpage.editmypostclick();
  	 		logger.info("Edit button of my post page clicked and edit post page opened");

  	    	String title = "";
  			String imageurl = "";
  			String post = "";

  			mypostpage.entertitle(title);
  	        mypostpage.editallpostimageurl(imageurl);
  	        mypostpage.editentermypost(post);
  	      logger.info("Entered invalid details for editing");
  	       boolean value= mypostpage.btnsubmiteditmypostpageINVALIDATA();
  	       System.out.println(value);



  	     if (!value)
  	     {
  	      Assert.assertFalse(value,"submit not enabled");
  	     }
  	     else
  	  	   Assert.assertTrue(value,"submit  enabled");

  	  logger.info("Submit not enabled and cannot edit");


  		}

  		   @Test(priority=3)
  		   public void VerifyDeletePostInMyPostpage() throws InterruptedException
  		   {
  			mypostpage.deletemypostclick();
  		     logger.info("Delete button of Mypost page clicked");
  	        Assert.assertEquals(driver.switchTo().alert().getText(),"Post deleted successfully");
  	        driver.switchTo().alert().accept();
  	      logger.info("Post deleted successfully");

  		   }



}
