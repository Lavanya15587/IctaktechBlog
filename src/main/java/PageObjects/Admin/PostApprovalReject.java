package PageObjects.Admin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utilities.pageutility;

public class PostApprovalReject {
    //2 posts must be there
    WebDriver driver;


    @FindBy(xpath = "//a[contains(text(),'New post')]")
    private WebElement newpostlink;
    @FindBy(xpath = "/html/body/app-root/app-pendingapproval/div[2]/li[2]/div/div/div/button[1]")
    private WebElement btnApprove;

    @FindBy(xpath = "/html/body/app-root/app-pendingapproval/div[2]/li[3]/div/div/div/button[2]")

    private WebElement btnReject;
    @FindBy(xpath = "/html/body/app-root/app-comment/form/div/textarea")

    private WebElement txtcomment;
    @FindBy(xpath = "//button[contains(text(),'send')]")
    private WebElement btnsend;

    @FindBy(xpath = "//p[contains(text(),'Actions')]")
    private WebElement actiondrpdown;

    @FindBy(xpath = "//a[contains(text(),'Pending Approvals')]")
    private WebElement pendingapproval;

    @FindBy(xpath = "//body/app-root[1]/app-usernewpost[1]/form[1]/div[3]/input[1]")
    private WebElement txtimageurluser;

    @FindBy(xpath = "/html/body/app-root/app-usernewpost/form/div[4]/select")
    private WebElement categorydrpdownuser;

    @FindBy(xpath = "//body/app-root[1]/app-usernewpost[1]/form[1]/div[5]/textarea[1]")
    private WebElement txtpostuser;

    @FindBy(xpath = "/html/body/app-root/app-usernewpost/form/button")
    private WebElement sendforapproval;

    @FindBy(xpath = "/html/body/app-root/app-pendingapproval/div[1]/h2")
    private WebElement title;
    @FindBy(xpath = "//h5[contains(text(),'WEBSITE  APPLICATIONS')]")
    private WebElement posttile;



    public PostApprovalReject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    //selecting from Actions menu
    public void selectactionsdropdown() throws InterruptedException {
		pageutility.waitForElementTobeVisible(driver,actiondrpdown,150);

        actiondrpdown.click();
		pageutility.waitForElementTobeVisible(driver,pendingapproval,150);

        Actions act = new Actions(driver);
        act.click(pendingapproval).perform();

    }


    //entering image url in new post
    public void enterimageurluser(String imageurl) {
		pageutility.waitForElementTobeVisible(driver,txtimageurluser,50);

        txtimageurluser.clear();
        txtimageurluser.sendKeys(imageurl);
    }

    public String getTitleOfPage(){
		pageutility.waitForElementTobeVisible(driver,title,50);

        return title.getText();
    }


    //entering category in new post

    public void selectcategory() throws InterruptedException {
		pageutility.waitForElementTobeVisible(driver,categorydrpdownuser,50);

        Select drpcategory = new Select(categorydrpdownuser);
        drpcategory.selectByIndex(0);
    }

    //entering post in text area
    public void enternewpostuser(String post) {
		pageutility.waitForElementTobeVisible(driver,txtpostuser,50);

        txtpostuser.clear();
        txtpostuser.sendKeys(post);
    }


    //SELECT NEWPOST LINK
    public void NewPostLink() throws InterruptedException {
		pageutility.waitForElementTobeVisible(driver,newpostlink,50);

    	newpostlink.click();

    }

    public void clickapproval() throws InterruptedException {
		pageutility.waitForElementTobeVisible(driver,posttile,50);


    	  JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript("arguments[0].scrollIntoView(true);", posttile);

  		pageutility.waitForElementTobeVisible(driver,btnApprove,50);

    	JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", btnApprove);

    }


    public void sendforapproval() throws InterruptedException {

  		pageutility.waitForElementTobeVisible(driver,sendforapproval,50);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", sendforapproval);
    }

    public void clickreject() throws InterruptedException {

  		pageutility.waitForElementTobeVisible(driver,posttile,50);

  	  JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", posttile);
  		pageutility.waitForElementTobeVisible(driver,btnReject,50);

    	JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", btnReject);



    }

    public void entercomments(String comment) {
  		pageutility.waitForElementTobeVisible(driver,txtcomment,50);

        txtcomment.clear();
        txtcomment.sendKeys(comment);


    }

    public void clicksend() throws InterruptedException {
  		pageutility.waitForElementTobeVisible(driver,btnsend,50);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", btnsend);
    }
}
