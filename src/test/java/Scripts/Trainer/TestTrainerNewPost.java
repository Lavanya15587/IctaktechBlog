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
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.SignUpPage;
import PageObjects.Trainer.TrainerMyPostPage;
import PageObjects.Trainer.TrainerNewPostPage;
import Scripts.TestBase;
import Utilities.ExcelUtility;

public class TestTrainerNewPost extends TestBase {
    LoginPage objLogin;
    SignUpPage objSignUp;
    TrainerNewPostPage objNewPost;
    TrainerMyPostPage objMyPost;
    HomePage objHomePage;

    @AfterTest
    void afterTestDone(){
        objLogin = null;
        objMyPost = null;
        objSignUp = null;
        objNewPost = null;
    }


    @Test(priority = 1)
    public void verifyNewPost() throws IOException, InterruptedException {
        loginToUser();

        // Go to new post.
        objMyPost = new TrainerMyPostPage(driver);
        objMyPost.clickOnNewPost();

        Thread.sleep(WEBDRIVER_WAIT_TIME);

        for (int i = 9; i < 12; i++) {
            // update new post
            objNewPost=new TrainerNewPostPage(driver);
            String url = driver.getCurrentUrl();
            String Title=ExcelUtility.getTrainerCellData(i,0);
            String Image=ExcelUtility.getTrainerCellData(i,1);
            String Post=ExcelUtility.getTrainerCellData(i,2);
            objNewPost.setTitle(Title);
            objNewPost.setImage(Image);
            objNewPost.setCategory();
            objNewPost.setPost(Post);
            Thread.sleep(2000);
            objNewPost.clickSubmit();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WEBDRIVER_WAIT_TIME_SEC));

            WebDriverWait w = new WebDriverWait(driver, 3);

            if(w.until(ExpectedConditions.alertIsPresent())==null) {
                Assert.assertFalse(false, "We should have got alert");
            }
            else {
                Assert.assertEquals("New Post Added", driver.switchTo().alert().getText());
                driver.switchTo().alert().accept();
            }

            driver.get(url);
        }
    }

    private void loginToUser() throws InterruptedException, IOException {
        Actions act = new Actions(driver);
        objLogin = new LoginPage(driver);
        Thread.sleep(WEBDRIVER_WAIT_TIME);
        objLogin.selectLoginDropdown();
        String username = ExcelUtility.getTrainerCellData(0, 0);
        String password = ExcelUtility.getTrainerCellData(0, 1);
        objLogin.loginToUser(username,password);

        // Check the url
        String strUrl = driver.getCurrentUrl();
        Assert.assertEquals(strUrl,"http://64.227.132.106/mypost");

        // Check the title of page.
        String expectedTitle = AutomationConstants.HOMEPAGETITLE;
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        Thread.sleep(WEBDRIVER_WAIT_TIME);

    }


}
