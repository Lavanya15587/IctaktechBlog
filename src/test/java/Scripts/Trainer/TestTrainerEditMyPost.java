package Scripts.Trainer;

import static Scripts.Utils.WEBDRIVER_WAIT_TIME;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Constants.AutomationConstants;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.Trainer.TrainerEditPost;
import PageObjects.Trainer.TrainerMyPostPage;
import Scripts.TestBase;
import Utilities.ExcelUtility;

public class TestTrainerEditMyPost extends TestBase {
    LoginPage objLogin;
    TrainerMyPostPage objMyPost;
    TrainerEditPost objEdit;
    HomePage objHome;

  @Test(priority=1)
    public void verifyEditMyPost() throws IOException, InterruptedException {
        loginToUser();

        Thread.sleep(WEBDRIVER_WAIT_TIME);

        objMyPost=new TrainerMyPostPage(driver);
        objMyPost.editMyPost();

        objEdit = new TrainerEditPost(driver);
        String url=driver.getCurrentUrl();
        String title=ExcelUtility.getTrainerCellData(10,3);
        String image=ExcelUtility.getTrainerCellData(10,4);
        String post=ExcelUtility.getTrainerCellData(10,5);
        objEdit.setTitle(title);
        objEdit.setImage(image);
        objEdit.setPostDesc(post);
        objEdit.setSubmit();
        String alertMessage=driver.switchTo().alert().getText();
        String expectedAlert=AutomationConstants.EDITEDALERT;
        Assert.assertEquals(expectedAlert,alertMessage);
        Alert al=driver.switchTo().alert();
        al.accept();
        Thread.sleep(WEBDRIVER_WAIT_TIME);

  }

  @Test(priority=2)
  public void verifyEditMyPostWithoutContents(){

  }

    public void loginToUser() throws IOException, InterruptedException {
        driver.navigate().refresh();
        Actions act=new Actions(driver);
        objLogin=new LoginPage(driver);
        objLogin.selectLoginDropdown();
        String username= ExcelUtility.getTrainerCellData(0,0);
        String password=ExcelUtility.getTrainerCellData(0,1);
        objLogin.loginToUser(username,password);
        String expectedTitle= AutomationConstants.HOMEPAGETITLE;
        String actualTitle=driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }

}
