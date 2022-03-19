package Scripts.Trainer;

import static Scripts.Utils.WEBDRIVER_WAIT_TIME;
import static Scripts.Utils.WEBDRIVER_WAIT_TIME_SEC;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import Constants.AutomationConstants;
import PageObjects.LoginPage;
import PageObjects.SignUpPage;
import PageObjects.Trainer.TrainerMyPostPage;
import PageObjects.Trainer.TrainerNewPostPage;
import Scripts.TestBase;
import Utilities.ExcelUtility;

public class TestTrainerLogin extends TestBase {
    LoginPage objLogin;
    SignUpPage objSignUp;
    TrainerNewPostPage objNewPost;
    TrainerMyPostPage objMyPost;

    @AfterTest
    void afterTest() {
        objLogin = null;
        objMyPost = null;
        objSignUp = null;
        objNewPost = null;
    }


    @Test(priority = 0)
    public void verifyInvalidUserInvalidPassword() throws IOException, InterruptedException {
        Actions act = new Actions(driver);
        objLogin = new LoginPage(driver);
        Thread.sleep(WEBDRIVER_WAIT_TIME);
        objLogin.selectLoginDropdown();
        String username = ExcelUtility.getTrainerCellData(4, 0);
        String password = ExcelUtility.getTrainerCellData(4, 1);
        objLogin.loginToUser(username,password);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WEBDRIVER_WAIT_TIME_SEC));

        WebDriverWait w = new WebDriverWait(driver, WEBDRIVER_WAIT_TIME_SEC);

        if (w.until(ExpectedConditions.alertIsPresent()) == null) {
            Assert.assertFalse(false, "We should have got alert");
        } else {
            Assert.assertEquals("User not found", driver.switchTo().alert().getText());
            driver.switchTo().alert().accept();
        }
    }

    @Test(priority = 1)
    public void verifyValidUserInvalidPassword() throws IOException, InterruptedException {
        Actions act = new Actions(driver);
        objLogin = new LoginPage(driver);
        Thread.sleep(WEBDRIVER_WAIT_TIME);
        String username = ExcelUtility.getTrainerCellData(1, 0);
        String password = ExcelUtility.getTrainerCellData(1, 1);
        objLogin.loginToUser(username,password);

        // user should be in login page only
        String strUrl = driver.getCurrentUrl();
        Assert.assertEquals(strUrl, "http://64.227.132.106/login");

        driver.navigate().refresh();
    }

    @Test(priority = 2)
    public void verifyValidLogin() throws IOException, InterruptedException {
        Actions act = new Actions(driver);
        objLogin = new LoginPage(driver);
        Thread.sleep(WEBDRIVER_WAIT_TIME);
        String username = ExcelUtility.getTrainerCellData(0, 0);
        String password = ExcelUtility.getTrainerCellData(0, 1);
        objLogin.loginToUser(username,password);

        // Check the url
        String strUrl = driver.getCurrentUrl();
        Assert.assertEquals(strUrl, "http://64.227.132.106/mypost");
        // Check the title of page.
        String expectedTitle = AutomationConstants.HOMEPAGETITLE;
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        Thread.sleep(WEBDRIVER_WAIT_TIME);
        logoutAfterTest();
    }


//    @Test(priority = 4)
//    public void verifyAdminLogin() throws IOException, InterruptedException {
//        Actions act = new Actions(driver);
//        objLogin.selectLoginDropdown();
//        Thread.sleep(WEBDRIVER_WAIT_TIME);
//
//        objLogin = new LoginPage(driver);
//        String username = ExcelUtility.getCellDataDateFormat(5, 0);
//        String password = ExcelUtility.getCellDataDateFormat(5, 1);
//        objLogin.setUserName(username);
//        objLogin.setPassword(password);
//        objLogin.clickLogin();
//        // Check the url
//        String strUrl = driver.getCurrentUrl();
//        Assert.assertEquals(strUrl, "http://64.227.132.106/admin");
//        // Check the title of page.
//        // String expectedTitle = AutomationConstants.HOMEPAGETITLE;
//        // String actualTitle = driver.getTitle();
//        //Assert.assertEquals(expectedTitle, actualTitle);
//        Thread.sleep(WEBDRIVER_WAIT_TIME);
//        logoutAfterTest();
//    }
/*
   @Test(priority = 5)
    public void verifyLoginWithoutPassword() throws InterruptedException, IOException {
        Actions act = new Actions(driver);
        Thread.sleep(WEBDRIVER_WAIT_TIME);
        objLogin.selectLoginDropdown();
        Thread.sleep(WEBDRIVER_WAIT_TIME);
        objLogin = new LoginPage(driver);
        String username = ExcelUtility.getCellData(3, 0);
        String password="";
        objLogin.setUserName(username);
        objLogin.setPassword(password);
        objLogin.clickLogin();
        Thread.sleep(WEBDRIVER_WAIT_TIME);

        String expectedTitle = AutomationConstants.PASSWORDASSERTTION;
        String actualTitle = objLogin.validationAssertPassword();
        Assert.assertEquals(expectedTitle,actualTitle);
       //Assert.assertEquals(expectedurl,actualTitle);
       Thread.sleep(WEBDRIVER_WAIT_TIME);
    }

  */

   /* @Test(priority = 6)
    public void verifyLoginWithoutUsernameWithoutPassword() throws InterruptedException, IOException {
        Actions act = new Actions(driver);
        objLogin = new LoginPage(driver);
        String username=" ";
        String password="";
      objLogin.setUserName(username);
      objLogin.setPassword(password);

        objLogin.clickLogin();
        Thread.sleep(WEBDRIVER_WAIT_TIME);
        String usernameAssertExpectedTitle="This field required";
        String usernameAssertActualTitle=objLogin.validationAssertUsername();
        Assert.assertEquals(usernameAssertExpectedTitle,usernameAssertActualTitle);
        Thread.sleep(WEBDRIVER_WAIT_TIME);
        String passwordAssertExpectedTitle = "This field required";
        String passwordAssertActualTitle = objLogin.validationAssertPassword();
        Assert.assertEquals(passwordAssertExpectedTitle, passwordAssertActualTitle);
        Thread.sleep(WEBDRIVER_WAIT_TIME);
    }

    */
        private void logoutAfterTest () {
            // Logout
            objMyPost = new TrainerMyPostPage(driver);
            objMyPost.logout();

            // Check the url
            String homeUrl = driver.getCurrentUrl();
            Assert.assertEquals(homeUrl, "http://64.227.132.106/#");
        }
    }

