package PageObjects.Trainer;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrainerMyPostPage {
    private static final long WEBDRIVER_WAIT_TIME_SEC = 3000;
    WebDriver driver;
    @FindBy(xpath="/html/body/app-root/app-trainerpost/app-header/nav/div/div/ul/li[5]/a")
    public WebElement newpost;

    @FindBy(xpath="/html/body/app-root/app-mypost/div[2]/li[1]/div/div/div/button[1]")
    private WebElement Edit;


    @FindBy(xpath="//*[@id=\"exampleInputEmail1\"]")
    private WebElement Title;

    @FindBy(xpath="//*[@id=\"exampleInputPassword1\"]")
    private WebElement Image;

    @FindBy(xpath="/html/body/app-root/app-editpost/form/div[4]/textarea")
    private WebElement PostDesc;

    @FindBy(xpath="/html/body/app-root/app-editpost/form/button")
    private WebElement SubmitBtn;

    @FindBy(xpath = "/html/body/app-root/app-mypost/div[2]/li[1]/div/div/div/button[2]")
    private WebElement Delete;




    @FindBy(xpath="/html/body/app-root/app-mypost/div[2]/li[1]/div/div/div/button[1]")
    private WebElement editBtn;


    @FindBy(xpath = "/html/body/app-root/app-mypost/app-header/nav/div/div/ul/li[10]/a")
    private WebElement logout;


    public TrainerMyPostPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void clickEdit()
    {
        Edit.click();
    }

    public void setTitle(String strTitle)
    {
        Title.sendKeys(strTitle);
    }

    public void setImage(String strImage)
    {
        Image.sendKeys(strImage);
    }

    public void setPostDesc(String strPostDesc)
    {
        PostDesc.sendKeys(strPostDesc);
    }

    public void submit()
    {
        SubmitBtn.click();
    }
    public void clickDelete()
    {
        Delete.click();
    }

    public void logout()
    {
        logout.click();
    }

    public void deleteAllPost() throws InterruptedException {
        WebElement button = getNextDeleteButton();
        while (button != null){
            // scroll to button.
            System.out.println("Found Delete Button "+ button.getText());
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);Thread.sleep(3000);

            // Delete action
            button.click();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WEBDRIVER_WAIT_TIME_SEC));
            WebDriverWait w = new WebDriverWait(driver, WEBDRIVER_WAIT_TIME_SEC);

            // Accept Delete
            if(w.until(ExpectedConditions.alertIsPresent())==null) {
              System.out.println("We should have got alert");
            }
            else {
                driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();
            }

            // Refresh page.
            driver.navigate().refresh();

            // Get next delete.
            if (w.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("button")))) == null){
                System.out.println("page refresh failed...");
            }else{
                Thread.sleep(2000);
                button = getNextDeleteButton();
            }
        }

    }

    private WebElement getNextDeleteButton(){
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        int count1 =0;
        for(WebElement button : buttons){
            if(button.getText().equals("Delete")){
               return button;
            }
        }

        return null;
    }

    public void clickOnNewPost(){
        driver.findElement(By.xpath("/html/body/app-root/app-mypost/app-header/nav/div/div/ul/li[5]/a")).click();
    }

    public void  editMyPost() throws InterruptedException {
        Thread.sleep(WEBDRIVER_WAIT_TIME_SEC);
        WebElement button = getNextEditButton();
        if (button != null) {
            // scroll to button.

            System.out.println("Found Edit Button " + button.getText());
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

            Thread.sleep(1000);
            // Edit action
            button.click();
            Thread.sleep(2000);

        }
    }

    private WebElement getNextEditButton(){
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        int count1 =0;
        for(WebElement button : buttons){
            if(button.getText().equals("Edit")){
                return button;
            }
        }

        return null;
    }
}
