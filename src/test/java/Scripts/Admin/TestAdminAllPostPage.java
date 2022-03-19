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

public class TestAdminAllPostPage extends TestBase {


	  Logger logger=LogManager.getLogger(TestAdminAllPostPage.class);
    Loginpage login;
    AllPostsPage allpostpage;
    ActionsPage action;
    @AfterTest
    void afterTest() {
        login = null;
        allpostpage = null;
        action=null;
    }





  //LOGIN

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




  //INVALID DATA


  @Test(priority=1)
  public void VerifyEditNewPostInAllPostpageINVALIDATA() throws InterruptedException, IOException
  {
  	 login=new Loginpage(driver);
  	 action=new ActionsPage(driver);


  	VerifyLoginAsAdmin();
  	logger.info("Admin login successful");
  	allpostpage=new AllPostsPage(driver);

  	allpostpage.editallpostclick();
  	logger.info("Edit button of allpost page clicked and edit post opened");
  	String title = "";
  	String imageurl = "";
  	String post = "";

      allpostpage.entertitle(title);


      allpostpage.editallpostimageurl(imageurl);

      allpostpage.editenterallpost(post);
      logger.info("Entered invalid details for editing");

     boolean value= allpostpage.btnsubmiteditallpostpageINVALIDATA();

     if (!value)
     {
      Assert.assertFalse(value,"submit not enabled");
     }
     else
  	   Assert.assertTrue(value,"submit  enabled");

  logger.info("Submit not enabled and cannot edit");
     }



  //valid data

  @Test(priority=2)
  public void VerifyEditNewPostInAllPostpage() throws InterruptedException, IOException
  {
  	allpostpage=new AllPostsPage(driver);


  	allpostpage.editallpostclick();
  	logger.info("Edit button of allpost page clicked and edit post opened");


  	String title = ExcelUtility.getAdminCellData(5, 0);
  	String imageurl = ExcelUtility.getAdminCellData(5, 1);
  	String post = ExcelUtility.getAdminCellData(5, 3);

      allpostpage.entertitle(title);


      allpostpage.editallpostimageurl(imageurl);
      allpostpage.editenterallpost(post);
      logger.info("Entered valid details for editing");
      allpostpage.btnsubmiteditallpostpage();
      logger.info("Submit button clicked");
      WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent());
      Assert.assertEquals(driver.switchTo().alert().getText(),"Updated Successfully");
  	driver.switchTo().alert().accept();

    logger.info("Updated successfully");
  }

     @Test(priority=3)
     public void VerifyDeletePostInAllPostpage() throws InterruptedException
     {
  	allpostpage.deleteallpostclick();
    logger.info("Delete button of Allpost page clicked");
      logger.info("Post deleted successfully");
     }

}
