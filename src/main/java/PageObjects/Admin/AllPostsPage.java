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

//CAN DELETE GENERAL METHODS FOR ADDING POST
public class AllPostsPage {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"nav\"]")
    private WebElement home;

    @FindBy(xpath = "//p[contains(text(),'Actions')]")
    private WebElement actiondrpdown;

    @FindBy(xpath = "//a[contains(text(),'New post')]")
    private WebElement newpostlink;

    @FindBy(xpath = "//a[contains(text(),'My posts')]")
    private WebElement myPost;

    @FindBy(xpath = "//a[contains(text(),'Pending Approvals')]")
    private WebElement pendingApprovals;

    @FindBy(xpath = "//input[@id='exampleInputEmail1']")
    private WebElement txttitle;


    @FindBy(xpath = "//body/app-root[1]/app-newpost[1]/form[1]/div[3]/input[1]")
    private WebElement txtimageurl;
    @FindBy(xpath = " //body/app-root[1]/app-editpost[1]/form[1]/div[3]/input[1]")
    private WebElement editallposttxtimageurl;


    @FindBy(xpath = "/html/body/app-root/app-newpost/form/div[4]/select")
    private WebElement categorydrpdown;

    @FindBy(xpath = "//body/app-root[1]/app-newpost[1]/form[1]/div[5]/textarea[1]")
    private WebElement txtpost;
    @FindBy(xpath = "//body/app-root[1]/app-editpost[1]/form[1]/div[4]/textarea[1]")
    private WebElement editallposttxtpost;

    @FindBy(xpath = "/html/body/app-root/app-newpost/form/button")
    private WebElement btnsubmitnewpost;
    @FindBy(xpath = "/html/body/app-root/app-editpost/form/button")
    private WebElement editallpostbtnsubmitnewpost;

    @FindBy(xpath = "/html/body/app-root/app-admin/div[1]/h2")
    private WebElement ALLPosttitle;


    @FindBy(xpath = "//a[contains(text(),'All posts')]")
    private WebElement Allpostslink;

    @FindBy(xpath = "/html/body/app-root/app-admin/div[2]/li[5]/div/div/div/button[1]")
    private WebElement editallpost;
    @FindBy(xpath = "/html/body/app-root/app-admin/div[2]/li[5]/div/div/div/button[2]")
    private WebElement deleteallpost;

    @FindBy(xpath = "/html/body/app-root/app-admin/app-header/nav/div/div/ul/li[11]/a")
    private WebElement logout;

    public AllPostsPage(WebDriver driver) {
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

    //selecting from Actions menu
    public void selectPendingApprvalDropDown() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,actiondrpdown,50);
        actiondrpdown.click();
		pageutility.waitForElementTobeVisible(driver,pendingApprovals,50);

        Actions act = new Actions(driver);
        act.click(pendingApprovals).perform();
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
		pageutility.waitForElementTobeVisible(driver,editallposttxtimageurl,50);

        editallposttxtimageurl.clear();
        editallposttxtimageurl.sendKeys(imageurl);
    }

    //entering category in new post

    public void selectcategory() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,categorydrpdown,50);
        Select drpcategory = new Select(categorydrpdown);
        drpcategory.selectByVisibleText("SPACE");
    }


    //click submit in new post
    public void btnsubmit() {

        btnsubmitnewpost.sendKeys(Keys.RETURN);
    }

    //click submit in edit post
    public void btnsubmiteditallpostpage() {

        editallpostbtnsubmitnewpost.sendKeys(Keys.RETURN);

    }


    //CLICK SUBMIT EDIT POST INVALI DATA
    public Boolean btnsubmiteditallpostpageINVALIDATA() throws InterruptedException {

pageutility.waitForElementTobeVisible(driver,editallpostbtnsubmitnewpost,50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", editallpostbtnsubmitnewpost);

        if (editallpostbtnsubmitnewpost.isEnabled())

            return true;
        else
            return false;


    }

    //entering post in text area
    public void enternewpost(String post) {
		pageutility.waitForElementTobeVisible(driver,txtpost,50);

        txtpost.clear();
        txtpost.sendKeys(post);
    }

    public void clickMyPosts(){
		pageutility.waitForElementTobeVisible(driver,myPost,50);

        myPost.click();
    }

    //entering post in text area when editing
    public void editenterallpost(String post) {
		pageutility.waitForElementTobeVisible(driver,editallposttxtpost,50);

        editallposttxtpost.clear();
        editallposttxtpost.sendKeys(post);
    }

    //welcome to ALLPOSTS
    public String allposttext() {
        return ALLPosttitle.getText();
    }

    //CLICKING edit button in homepage
    public void editallpostclick() throws InterruptedException {
		pageutility.waitForElementTobeVisible(driver,Allpostslink,50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", Allpostslink);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", Allpostslink);

		pageutility.waitForElementTobeVisible(driver,editallpost,50);

        JavascriptExecutor ijs = (JavascriptExecutor) driver;
        ijs.executeScript("arguments[0].scrollIntoView(true);", editallpost);
        JavascriptExecutor pjs = (JavascriptExecutor) driver;
        pjs.executeScript("arguments[0].click();", editallpost);


    }


    //CLICKING delete button in homepage
    public void deleteallpostclick() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,Allpostslink,50);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", Allpostslink);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", Allpostslink);

		pageutility.waitForElementTobeVisible(driver,deleteallpost,50);

        JavascriptExecutor ijs = (JavascriptExecutor) driver;
        ijs.executeScript("arguments[0].scrollIntoView(true);", deleteallpost);
        JavascriptExecutor pjs = (JavascriptExecutor) driver;
        pjs.executeScript("arguments[0].click();", deleteallpost);
        //deleteallpost.click();


    }
    public void logout()
    {
        logout.click();
    }

}