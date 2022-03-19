package PageObjects.Admin;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.pageutility;

//import Scripts.TestBase;

public class Loginpage
{
WebDriver driver;



@FindBy(xpath="//div[@id='navbarSupportedContent']")
private WebElement headerlinkdiv;

@FindBy(xpath="//body/app-root[1]/app-footer[1]/footer[1]/div[1]")
private WebElement footerlinkdiv;
@FindBy(xpath="//body/app-root[1]/app-home[1]/div[1]/div[6]")
private WebElement feedbackdiv;


	@FindBy(xpath="//app-header/nav[1]/div[1]/div[1]/ul[1]/li[11]/a[1]")
	private WebElement logindropdown;
	@FindBy(xpath="//a[contains(text(),'logout')]")

	private WebElement logout;

    @FindBy(xpath = "//*[@id=\"nav\"]")
    private WebElement home;

	 @FindBy(xpath="//*[@id=\"user\"]")
	 private WebElement txtloginasuser;

	 @FindBy(xpath="//a[contains(text(),'Login')]")
	 private WebElement login;

	@FindBy(xpath="//input[@id='pwd']")
	private WebElement txtpassword;

	@FindBy(xpath="//input[@id='user']")
	private WebElement txtusername;

	@FindBy(xpath="//button[@id='logbut']")
	private WebElement btnlogin;

	@FindBy(xpath="	//h2[contains(text(),'All Posts')]")
	private WebElement allposttext;

	@FindBy(xpath="	//h2[contains(text(),'My Posts')]")
	private WebElement myposttext;

	@FindBy(xpath="//*[@id=\"log\"]/h3")
	private WebElement logintitle;
	//*[@id="log"]/h3

	public Loginpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}


//headerlinks count
public int headerlinks()
{
	List<WebElement> elements=driver.findElements(By.xpath("//a[contains(@class, 'nav-link')]"));
    int count=elements.size();
    return count;

}
//feedback-textboxes count
public int TextboxesCount()
{
	 List<WebElement> elements=driver.findElements(By.xpath("//input"));
	int textboxes=elements.size();
	return textboxes;


}

public int noOfSubmitButton(){
    List<WebElement> elements=driver.findElements(By.xpath("//button"));
    int count=elements.size();
    return count;
}


	public void enterusername(String username)
	{
		pageutility.waitForElementTobeVisible(driver,txtusername,50);

		txtusername.clear();
		txtusername.sendKeys(username);
	}

	public boolean loginbtnenabled()
	{

		pageutility.waitForElementTobeVisible(driver,login,50);

        if(login.isEnabled())

		return false;
        else
		return true;
	}


	public void enterpassword(String password)
	{


		pageutility.waitForElementTobeVisible(driver,txtpassword,50);
		txtpassword.sendKeys(password);
	}
	public void clicklogin()


	{
		pageutility.waitForElementTobeVisible(driver,btnlogin,50);

		btnlogin.click();
	}
	public void selectlogindropdown() throws InterruptedException
	{
		pageutility.waitForElementTobeVisible( driver,logindropdown,50);

		logindropdown.click();
		//pageutility.hitenter(driver,login);
		pageutility.waitForElementTobeVisible( driver,login,50);

		Actions act=new Actions(driver);
		act.click(login).perform();

	}
	public String allposttext()
	{

		pageutility.waitForElementTobeVisible(driver,allposttext,50);

	return allposttext.getText();


	}


	public String myposttext()
	{
		pageutility.waitForElementTobeVisible(driver,myposttext,50);

	return myposttext.getText();


	}

	public void logout() throws InterruptedException
	{
		 JavascriptExecutor executor = (JavascriptExecutor)driver;
  	     executor.executeScript("arguments[0].click();", logout);
  	     //Thread.sleep(2000);

	}
	public String assertusername() throws InterruptedException
	{
		pageutility.waitForElementTobeVisible(driver,txtusername,50);

		   String username=txtusername.getText();
		   System.out.println(username);

		return username;
	}

	public String assertpassword() throws InterruptedException
	{
		pageutility.waitForElementTobeVisible(driver,txtpassword,50);

		   String pwd=txtpassword.getText();
		return pwd;

	}
	}

