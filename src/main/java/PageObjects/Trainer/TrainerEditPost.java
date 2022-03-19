package PageObjects.Trainer;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TrainerEditPost {
    WebDriver driver;
    TrainerEditPost objEdit;

    @FindBy(xpath="/html/body/app-root/app-editpost/form/div[1]/input")
    private WebElement title;

    @FindBy(xpath="/html/body/app-root/app-editpost/form/div[3]/input")
    private WebElement image;

    @FindBy(xpath="/html/body/app-root/app-editpost/form/div[4]/textarea")
    private WebElement postDesc;

    @FindBy(xpath="/html/body/app-root/app-editpost/form/button")
    private WebElement submit;


    public TrainerEditPost(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    public void setTitle(String strTitle) throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",title);
        Thread.sleep(3000);
        title.clear();
        title.sendKeys(strTitle);
    }
    public void setImage(String strImage)
    {
        image.clear();
        image.sendKeys(strImage);
    }
    public void setPostDesc(String strPost)
    {
        postDesc.clear();
        postDesc.sendKeys(strPost);
    }

    public void setSubmit() throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);" ,submit);
        Thread.sleep(2000);
        submit.click();
    }
}
