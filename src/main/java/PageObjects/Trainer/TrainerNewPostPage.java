package PageObjects.Trainer;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TrainerNewPostPage {
    WebDriver driver;

    @FindBy(id="exampleInputEmail1")
    private WebElement Title;

    @FindBy(xpath="/html/body/app-root/app-trainerpost/form/div[3]/input")
    private WebElement Image;

    @FindBy(xpath="/html/body/app-root/app-trainerpost/form/div[4]/select")
    private WebElement Category;

    @FindBy(xpath="/html/body/app-root/app-trainerpost/form/div[5]/textarea")
    private WebElement Post;

    @FindBy(xpath="/html/body/app-root/app-trainerpost/form/button")
    private WebElement Submit;

    @FindBy(xpath="/html/body/app-root/app-trainerpost/div/h2")
    private WebElement actualTitle;

    @FindBy(xpath="//*[@id=\"nav\"]")
    private WebElement Logout;


    public TrainerNewPostPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public void setTitle(String strTitle){
        Title.sendKeys(strTitle);

    }
    public void setImage(String imgurl){
        Image.sendKeys(imgurl);
    }
    public void setCategory(){
        Select categoryMenu=new Select(Category);
        categoryMenu.selectByValue("4: SPACE");

    }
    public void setPost(String postdesc){
        Post.sendKeys(postdesc);
    }
    public void clickSubmit() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Submit);
        Thread.sleep(500);
        Submit.click();
    }

    public void clickLogout(){
        Logout.click();
    }

    public String getHeading() {
        String actTitle = actualTitle.getText();
        return actTitle;
    }
}





