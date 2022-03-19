package PageObjects.Admin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utilities.pageutility;

public class AdminMyPostsPage {
    WebDriver driver;

    @FindBy(xpath = "//p[contains(text(),'Actions')]")
    private WebElement actiondrpdown;

    @FindBy(xpath = "//a[contains(text(),'New post')]")
    private WebElement newpostlink;

    @FindBy(xpath = "//input[@id='exampleInputEmail1']")
    private WebElement txttitle;

    @FindBy(xpath = "//body/app-root[1]/app-newpost[1]/form[1]/div[3]/input[1]")
    private WebElement txtimageurl;
    @FindBy(xpath = " //body/app-root[1]/app-editpost[1]/form[1]/div[3]/input[1]")
    private WebElement editMyposttxtimageurl;

    @FindBy(xpath = "/html/body/app-root/app-newpost/form/div[4]/select")
    private WebElement categorydrpdown;
    @FindBy(xpath = "//body/app-root[1]/app-newpost[1]/form[1]/div[5]/textarea[1]")
    private WebElement txtpost;
    @FindBy(xpath = "//body/app-root[1]/app-editpost[1]/form[1]/div[4]/textarea[1]")
    private WebElement editMyposttxtpost;

    @FindBy(xpath = "/html/body/app-root/app-newpost/form/button")
    private WebElement btnsubmitnewpost;
    @FindBy(xpath = "/html/body/app-root/app-editpost/form/button")
    private WebElement editMypostbtnsubmitnewpost;
    @FindBy(xpath = "/html/body/app-root/app-mypost/div[1]/h2")
    private WebElement MyPosttitle;

    @FindBy(xpath = " //a[contains(text(),'My posts')]")
    private WebElement MyPostlink;



    @FindBy(xpath = "/html/body/app-root/app-mypost/div[2]/li[1]/div/div/div/button[1]")

    private WebElement editMypost;

    @FindBy(xpath = "/html/body/app-root/app-mypost/div[2]/li[1]/div/div/div/button[2]")
    private WebElement deleteMypost;

    @FindBy(xpath = "/html/body/app-root/app-mypost/app-header/nav/div/div/ul/li[10]/a")
    private WebElement logout;

    public AdminMyPostsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //selecting from Actions menu
    public void selectactionsdropdown() throws InterruptedException {
		pageutility.waitForElementTobeVisible(driver,actiondrpdown,50);
        actiondrpdown.click();
		pageutility.waitForElementTobeVisible(driver,newpostlink,50);

        Actions act = new Actions(driver);
        act.click(newpostlink).perform();

    }

    //entering title in new post
    public void entertitle(String title) {
		pageutility.waitForElementTobeVisible(driver,txttitle,50);

        txttitle.clear();
        txttitle.sendKeys(title);
    }

//entering image url in new post

    public void enterimageurl(String imageurl) {
		pageutility.waitForElementTobeVisible(driver,txtimageurl,50);

        txtimageurl.clear();
        txtimageurl.sendKeys(imageurl);
    }

    //entering image url in edit  post
    public void editallpostimageurl(String imageurl) {
		pageutility.waitForElementTobeVisible(driver,editMyposttxtimageurl,50);
        editMyposttxtimageurl.clear();
        editMyposttxtimageurl.sendKeys(imageurl);
    }

    //entering category in new post

    public void selectcategory() throws InterruptedException {
		pageutility.waitForElementTobeVisible(driver,categorydrpdown,50);
        Select drpcategory = new Select(categorydrpdown);
        drpcategory.selectByIndex(1);
    }


    //click submit in new post
    public void btnsubmit() {
		pageutility.waitForElementTobeVisible(driver,btnsubmitnewpost,50);

        btnsubmitnewpost.sendKeys(Keys.RETURN);
    }

    //click submit in new post
    public void mypostlinkclick() {
		pageutility.waitForElementTobeVisible(driver,MyPostlink,50);

        MyPostlink.click();
    }

    //click submit in edit post
    public void btnsubmiteditmypostpage() {
		pageutility.waitForElementTobeVisible(driver,editMypostbtnsubmitnewpost,50);

        editMypostbtnsubmitnewpost.sendKeys(Keys.RETURN);

    }

    //entering post in text area
    public void enternewpost(String post) {
		pageutility.waitForElementTobeVisible(driver,txtpost,50);

        txtpost.clear();
        txtpost.sendKeys(post);
    }

    //entering post in text area when editing
    public void editentermypost(String post) {
		pageutility.waitForElementTobeVisible(driver,editMyposttxtpost,50);

        editMyposttxtpost.clear();
        editMyposttxtpost.sendKeys(post);
    }

    //welcome to MYPOSTS
    public String allposttext() {
        return MyPosttitle.getText();
    }

    //CLICKING edit button in homepage
    public void editmypostclick() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,MyPostlink,50);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", MyPostlink);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", MyPostlink);

		pageutility.waitForElementTobeVisible(driver,editMypost,50);

        JavascriptExecutor ijs = (JavascriptExecutor) driver;
        ijs.executeScript("arguments[0].scrollIntoView(true);", editMypost);
        JavascriptExecutor pjs = (JavascriptExecutor) driver;
        pjs.executeScript("arguments[0].click();", editMypost);
    }


    //CLICKING delete button in homepage
    public void deletemypostclick() throws InterruptedException {
		pageutility.waitForElementTobeVisible(driver,MyPostlink,50);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", MyPostlink);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", MyPostlink);
		pageutility.waitForElementTobeVisible(driver,deleteMypost,50);

        JavascriptExecutor ijs = (JavascriptExecutor) driver;
        ijs.executeScript("arguments[0].scrollIntoView(true);", deleteMypost);
        JavascriptExecutor pjs = (JavascriptExecutor) driver;
        pjs.executeScript("arguments[0].click();", deleteMypost);

    }

    //CLICK SUBMIT EDIT POST INVALId DATA
    public Boolean btnsubmiteditmypostpageINVALIDATA() throws InterruptedException {
		pageutility.waitForElementTobeVisible(driver,editMypostbtnsubmitnewpost,50);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", editMypostbtnsubmitnewpost);
        Thread.sleep(2000);
        if (editMypostbtnsubmitnewpost.isEnabled())
            return true;
        else
            return false;

    }
    public void logout()
    {
        logout.click();
    }
}
