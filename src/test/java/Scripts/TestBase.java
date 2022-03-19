package Scripts;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBase {
    public static Properties prop = null;
    public WebDriver driver;
 String driverPath = "C:\\Drivers/chromedriver.exe";
    //String driverpath = "/Users/naveenmurthy/Documents/Priya/FirefoxWebDriver/geckodriver";

    public static void  TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Parameters("browser")
    @BeforeClass
    public void onSetup(String browserName) {

    	  Logger logger=LogManager.getLogger(TestBase.class);

    	//PropertyConfigurator.configure("log4j.properties");

        Thread.currentThread().getStackTrace();

    	logger.info("onSetup is called....");

        TestBase();
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
        }
        if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", driverPath);
            driver = new FirefoxDriver();
        }
      // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        logger.info("Chrome browser opened and application is launched");
    }

    @AfterClass
    public void teardown() {
        driver.quit();


    }

}

