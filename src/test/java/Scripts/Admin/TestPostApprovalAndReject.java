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
import PageObjects.Admin.PostApprovalReject;
import Scripts.TestBase;
import Utilities.ExcelUtility;

public class TestPostApprovalAndReject extends TestBase {
	  Logger logger=LogManager.getLogger(TestAdminLogin.class);

	  Loginpage login;
	  AllPostsPage allpostpage;
      ActionsPage action;
       PostApprovalReject postapproval;

    @AfterTest
    void afterTest() {
        login = null;
        allpostpage = null;
        action=null;
        postapproval=null;
    }


	//ADDPOST


	public void VerifyAddnewPost() throws InterruptedException, IOException
	{

		postapproval=new PostApprovalReject(driver);

		action=new ActionsPage(driver);
		//Thread.sleep(3000);
         postapproval.NewPostLink();

       // Thread.sleep(4000);

		String title = ExcelUtility.getAdminCellData(3, 0);
		String imageurl = ExcelUtility.getAdminCellData(3, 1);
		//String category = ExcelUtility.getCellData(3, 2);
		String post = ExcelUtility.getAdminCellData(3, 3);


		action.entertitle(title);
		postapproval.enterimageurluser(imageurl);
		postapproval.selectcategory();
		postapproval.enternewpostuser(post);
		postapproval.sendforapproval();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(),"Post send for approval from admin");
		driver.switchTo().alert().accept();



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
	public void VerifyLogout() throws InterruptedException, IOException
	{
		login=new Loginpage(driver);
		login.logout();

	}


//LOGIN AS USER
	@Test(priority=1)
	public void VerifyLoginAsUser() throws InterruptedException, IOException
	{


		login=new Loginpage(driver);


		login.selectlogindropdown();
		logger.info("login clicked");

		String username = ExcelUtility.getAdminCellData(4, 0);
		String password = ExcelUtility.getAdminCellData(4, 1);

		login.enterusername(username);

		login.enterpassword(password);
		logger.info("Entere login details");

		login.clicklogin();
		logger.info("clicked login button");


		String myposttext=login.myposttext();
		Assert.assertEquals(myposttext,"My Posts");
		logger.info("login as user successful");



	}





	@Test(priority=2)
	public void VerifyPostApproval() throws InterruptedException, IOException
	{
		VerifyAddnewPost();
		VerifyAddnewPost();
		logger.info("new post added as user");
		logger.info("new post added as user");
		VerifyLogout();
		logger.info("logged out successful");
		VerifyLoginAsAdmin();
		logger.info("logged as admin");
		postapproval=new PostApprovalReject(driver);
		postapproval.selectactionsdropdown();
		logger.info("clicked  Actions menu and clickedpost approve/reject ");
		postapproval.clickapproval();
		logger.info("Pending post opened and clicked approve");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(),"Post approved");
		driver.switchTo().alert().accept();
		logger.info("Post approved message displayed");
		postapproval.entercomments("good");
		postapproval.clicksend();
		logger.info("Entered comments and clicked send button");
		WebDriverWait mywait = new WebDriverWait(driver, 15);
		mywait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(),"message send to user");
		driver.switchTo().alert().accept();
		logger.info("message send to user- displayed");



	}

	@Test(priority=3)
	public void VerifyPostReject() throws InterruptedException, IOException
	{

		postapproval=new PostApprovalReject(driver);
		postapproval.selectactionsdropdown();
		logger.info("clicked  Actions menu and clickedpost approve/reject ");
		postapproval.clickreject();
		logger.info("Pending post opened and clicked approve");
		String r=driver.switchTo().alert().getText();
		Assert.assertEquals(driver.switchTo().alert().getText(),"post rejected");
		driver.switchTo().alert().accept();
		logger.info("Post approved message displayed");
		postapproval.entercomments("bad");
		postapproval.clicksend();
		logger.info("Entered comments and clicked send button");
		WebDriverWait swait = new WebDriverWait(driver, 15);
		swait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(driver.switchTo().alert().getText(),"message send to user");
		driver.switchTo().alert().accept();
		logger.info("message send to user- displayed");


	}

}
