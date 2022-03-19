package PageObjects;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ExcelUtility;


public class LoginPage {
    WebDriver driver;

    @FindBy(xpath = "/html/body/app-root/app-home/app-header/nav/div/div/ul/li[11]/a")
    private WebElement selOption;

    @FindBy(xpath = "/html/body/app-root/app-home/app-header/nav/div/div/ul/li[11]/ul/li[1]/a")
    private WebElement login;


    @FindBy(id = "user")
    private WebElement emailId;
    @FindBy(id = "pwd")
    private WebElement password;
    @FindBy(id = "logbut")
    private WebElement Login;

    @FindBy(xpath = "//*[@id=\"log\"]/small[1]")
    private WebElement userNameTextTitle;

    @FindBy(id = "/html/body/app-root/app-login/form/small[2]")
    private WebElement pwdTxtTitle;

    @FindBy(xpath = "//*[@id=\"nav\"]")
    private WebElement Logout;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void selectLoginDropdown() {
        selOption.click();
        Actions act = new Actions(driver);
        act.click(login).perform();

    }

    public void setUserName(String strUserName) {
        emailId.sendKeys(strUserName);
    }

    public void clearUserName() {

        emailId.clear();
    }

    public void clearPwd() {
        password.clear();
    }
    //set password in textbox
    public void setPassword(String strPassword) {
        password.sendKeys(strPassword);
    }

    //click on login button
    public void clickLogin() {
        Login.click();
    }


    public void clickLogout() {

        Logout.click();
    }


    public String validationAssertPassword() {
        String pwdValidation= pwdTxtTitle.getText();
        return pwdValidation;

    }

    public String validationAssertUsername() {
        String usernameValidation = userNameTextTitle.getText();
        return usernameValidation;
    }

    public void loginToUser(String userName, String password) throws InterruptedException, IOException {
        this.setUserName(userName);
        this.setPassword(password);
        this.clickLogin();
    }

    public void loginAsAdmin() throws InterruptedException, IOException{
        this.selectLoginDropdown();
        String username = ExcelUtility.getAdminCellData(0, 0);
        String password = ExcelUtility.getAdminCellData(0, 1);
        this.loginToUser(username,password);
    }
}
