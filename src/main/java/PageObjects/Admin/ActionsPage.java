package PageObjects.Admin;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utilities.pageutility;

public class ActionsPage {

    WebDriver driver;


	@FindBy(xpath="//app-header/nav[1]/div[1]")
	private WebElement headerlinks;


	@FindBy(xpath="//body/app-root[1]/app-newpost[1]/form[1]")
	private WebElement form;

	@FindBy(xpath="//h2[contains(text(),'All Posts')]")
	private WebElement allposttext;

    @FindBy(xpath = "//p[contains(text(),'Actions')]")
    private WebElement actiondrpdown;

    @FindBy(xpath = "//a[contains(text(),'New post')]")
    private WebElement newpostlink;

    @FindBy(xpath = "//input[@id='exampleInputEmail1']")
    private WebElement txttitle;
    @FindBy(xpath="//a[contains(text(),'Add/Remove category')]")
	 private WebElement addaremovecategorydrpdown;

    @FindBy(xpath = "//body/app-root[1]/app-newpost[1]/form[1]/div[3]/input[1]")
    private WebElement txtimageurl;

    @FindBy(xpath = "//body/app-root[1]/app-editpost[1]/form[1]/div[3]/input[1]")
    private WebElement edittxtimageurl;

    @FindBy(xpath = "/html/body/app-root/app-newpost/form/div[4]/select")
    private WebElement categorydrpdown;

    @FindBy(xpath = "//body/app-root[1]/app-newpost[1]/form[1]/div[5]/textarea[1]")
    private WebElement txtpost;

    @FindBy(xpath = "/html/body/app-root/app-editpost/form/div[4]/textarea")
    private WebElement edithomepagetxtpost;

    @FindBy(xpath = "/html/body/app-root/app-newpost/form/button")
    private WebElement btnsubmitnewpost;

    @FindBy(xpath = "/html/body/app-root/app-editpost/form/button")
    private WebElement btnsubmiteditnewpost;

    @FindBy(xpath = "/html/body/app-root/app-home/div/div[1]/div/div/h2")
    private WebElement homepagetitle;

    @FindBy(xpath = "//*[@id=\"nav\"]")
    private WebElement home;


    @FindBy(xpath = "/html/body/app-root/app-singlepost/div/div[2]/div/div/button[1]")
    private WebElement edithomepost;

    @FindBy(xpath = "/html/body/app-root/app-singlepost/div/div[2]/div/div/button[2]")
    private WebElement deletehomepost;

    @FindBy(xpath = "//h5[contains(text(),'WEBSITE  APPLICATIONS')]")
    private WebElement POSTTITLE;
    @FindBy(xpath = "//h5[contains(text(),'MOBILE  APPLICATIONS')]")
    private WebElement POSTTITLEDEL;

    public ActionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


  //headerlink
    public int headerlinks()
    {
    	List<WebElement> elements=driver.findElements(By.xpath("//a[contains(@class, 'nav-link')]"));
        int count=elements.size();
        return count;

    }

    //form
    public int textboxescount()
    {

    	 List<WebElement> elements=driver.findElements(By.xpath("//input"));
         int count=elements.size();
         return count;
    }
    //textarea
    public int textareacount()
    {

        List<WebElement> elements=driver.findElements(By.xpath("//textarea"));
        int count=elements.size();
        return count;

    }

    //drpdown
    public int drpdowncount()
    {

    	  List<WebElement> elements=driver.findElements(By.xpath("//select"));
          int count=elements.size();
          return count;

    }
    
    public int noOfSubmitButton(){
        List<WebElement> elements=driver.findElements(By.xpath("//button"));
        int count=elements.size();
        return count;
    }
    //selecting from Actions menu
    public void selectactionsdropdown() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,actiondrpdown,50);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", actiondrpdown);

		pageutility.waitForElementTobeVisible(driver,newpostlink,50);

	     JavascriptExecutor Jexecutor = (JavascriptExecutor) driver;
	       Jexecutor.executeScript("arguments[0].click();", actiondrpdown);
       // Actions act = new Actions(driver);
        //act.click(newpostlink).perform();

    }


    //selecting from Actions menu addremovecateogry
    public void addremoveselect() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,actiondrpdown,50);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", actiondrpdown);

		pageutility.waitForElementTobeVisible(driver,addaremovecategorydrpdown,50);
        Actions act = new Actions(driver);
        act.click(addaremovecategorydrpdown).perform();

    }


    //entering title in new post
    public void entertitle(String title) {
		pageutility.waitForElementTobeVisible(driver,txttitle,50);

        txttitle.clear();
        txttitle.sendKeys(title);
    }

    //TITILE ASSERT
    public String titleAssert(String title) {
		pageutility.waitForElementTobeVisible(driver,txttitle,50);

        return txttitle.getText();

    }
    //entering image url in new post

    public void enterimageurl(String imageurl) {
		pageutility.waitForElementTobeVisible(driver,txtimageurl,50);

        txtimageurl.clear();
        txtimageurl.sendKeys(imageurl);
    }

    //entering image url in edit  post
    public void editimageurl(String imageurl) {
		pageutility.waitForElementTobeVisible(driver,edittxtimageurl,50);

        edittxtimageurl.clear();
        edittxtimageurl.sendKeys(imageurl);
    }

    //entering category in new post

    public void selectcategory() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,categorydrpdown,50);
        Select drpcategory = new Select(categorydrpdown);
        drpcategory.selectByIndex(1);
    }


    //click submit in new post
    public void btnsubmit() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,btnsubmitnewpost,50);

        JavascriptExecutor ijs = (JavascriptExecutor) driver;
        ijs.executeScript("arguments[0].scrollIntoView(true);", btnsubmitnewpost);
        JavascriptExecutor pjs = (JavascriptExecutor) driver;
        pjs.executeScript("arguments[0].click();", btnsubmitnewpost);
       // btnsubmitnewpost.click();
    }

    //click submit in new post(INVALID DATA)
    public boolean btnsubmitnotenabled() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,btnsubmitnewpost,50);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btnsubmitnewpost);

        if (btnsubmitnewpost.isEnabled())

        return true;
        else
		return false;


    }

    //click submit in edit post
    public void btnsubmiteditnewposthomepage() {

		pageutility.waitForElementTobeVisible(driver,btnsubmiteditnewpost,50);

        btnsubmiteditnewpost.sendKeys(Keys.RETURN);

    }

    ////click submit in edit post INVALID DATA
    public Boolean btnsubmiteditnewposthomepageINVALIDATA() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,btnsubmiteditnewpost,50);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", btnsubmiteditnewpost);

        if (btnsubmiteditnewpost.isEnabled())

            return true;
        else return false;


    }

    //entering post in text area
    public void enternewpost(String post) {

		pageutility.waitForElementTobeVisible(driver,txtpost,50);

        txtpost.clear();
        txtpost.sendKeys(post);
    }

    //entering post in text area when editing
    public void editenterpost(String post) {
		pageutility.waitForElementTobeVisible(driver,edithomepagetxtpost,50);

        edithomepagetxtpost.clear();
        edithomepagetxtpost.sendKeys(post);
    }

    //welcome to ICTAKTECHBLOG
    public String homepagetext() {
        return homepagetitle.getText();
    }


    public void home() {
		pageutility.waitForElementTobeVisible(driver,home,50);

        home.click();
    }

    //CLICKING edit button in homepage
    public void edithomeclick() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,home,50);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", home);


        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", home);

		pageutility.waitForElementTobeVisible(driver,POSTTITLE,150);

        JavascriptExecutor ijs = (JavascriptExecutor) driver;
        ijs.executeScript("arguments[0].scrollIntoView(true);", POSTTITLE);


        JavascriptExecutor pjs = (JavascriptExecutor) driver;
        pjs.executeScript("arguments[0].click();", POSTTITLE);

		pageutility.waitForElementTobeVisible(driver,edithomepost,50);

        edithomepost.click();
    }


    //CLICKING delete button in homepage
    public void deletehomeclick() throws InterruptedException {

		pageutility.waitForElementTobeVisible(driver,home,50);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", home);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", home);

		pageutility.waitForElementTobeVisible(driver,POSTTITLEDEL,150);

        JavascriptExecutor ijs = (JavascriptExecutor) driver;
        ijs.executeScript("arguments[0].scrollIntoView(true);", POSTTITLEDEL);


		 JavascriptExecutor pjs = (JavascriptExecutor) driver;
		 pjs.executeScript("arguments[0].click();", POSTTITLEDEL);
			pageutility.waitForElementTobeVisible(driver,deletehomepost,50);

        deletehomepost.click();
    }


}