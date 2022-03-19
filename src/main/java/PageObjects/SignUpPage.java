package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage {
    WebDriver driver;





    @FindBy(xpath="/html/body/app-root/app-home/app-header/nav/div/div/ul/li[11]/a")
    private WebElement selOptions;
    @FindBy(xpath="/html/body/app-root/app-home/app-header/nav/div/div/ul/li[11]/ul/li[2]/a")
    private WebElement signUp;
    //Identify elements
    @FindBy(id="first")
    private WebElement Name;

    @FindBy(xpath="//*[@id=\"sign\"]/select[1]")
    private WebElement Account;

    @FindBy(xpath="//*[@id=\"sign\"]/select[2]")
    private WebElement Qualification;

    @FindBy(id="em")
    private WebElement Email;

    @FindBy(id="pw")
    private WebElement Password;

    @FindBy(xpath="//*[@id=\"but\"]")
    private WebElement Submit;

    @FindBy(xpath="//*[@id=\"sign\"]/h3")
    private WebElement signupHeader;

    @FindBy(xpath="//*[@id=\"sign\"]/small[3]/b")
    private WebElement usernameValidMsg;
    @FindBy(xpath="//*[@id=\"sign\"]/small[4]/b ")
    private WebElement passwordValidMsg;

    //Initialization
    public  SignUpPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //Actions

    public void selectSignUpDrop()
    {
        selOptions.click();
        Actions act=new Actions(driver);
        act.click(signUp).perform();
    }

    public void setName(String strName){
        Name.sendKeys(strName);
    }
    public void setAccount(){
        Select acctDrp=new Select(Account);
        acctDrp.selectByIndex(1);
    }
    public void setQualification(){
        Select qualifyDrp=new Select(Qualification);
        qualifyDrp.selectByIndex(2);
    }
    public void setEmail(String strEmail){
        Email.sendKeys(strEmail);
    }
    public void setPassword(String strPassword){
        Password.sendKeys(strPassword);

    }

    public void clickSubmit(){
        Actions action = new Actions(driver);
        action.moveToElement(Submit).click().perform();
        //Submit.click();
    }

    public String setSignupHeader()
    {
        String actSignUpTitle=signupHeader.getText();
        return actSignUpTitle;
    }

    public String validUserText()
    {
        String usernameTextValidMsg=usernameValidMsg.getText();
        return usernameTextValidMsg;

    }

    public String validPasswordText()
    {
        String passwordTextValidMsg=passwordValidMsg.getText();
        return passwordTextValidMsg;

    }


}
