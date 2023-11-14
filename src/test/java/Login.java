import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Login {
    WebDriver driver = new ChromeDriver();


    @BeforeTest
    public void testOpenUrl() throws InterruptedException {

        //Open URL https://difc.globaltradingnetwork.com/web/login
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://difc.globaltradingnetwork.com/web/login");

        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test
    public void testEmptyLogin() throws InterruptedException {

        //Check Empty fields then login
        driver.findElement(By.name("username")).sendKeys("");


        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.id("LoginButton")).click();
        Thread.sleep(3000);

        String actualMessage = driver.findElement(By.xpath("//*[contains(text(),\"Enter\")]")).getText();
        Assert.assertEquals(actualMessage,"Enter Username & password");

        //Print message display on screen
        System.out.println("Message displayed on the screen for Empty fields login is : " + actualMessage);

    }

    @Test(priority = 1)
    public void testIncorrectLogin() throws InterruptedException {

        //Using random username/password then login

        driver.findElement(By.name("username")).sendKeys("Test");
        driver.findElement(By.name("password")).sendKeys("Test");
        driver.findElement(By.id("LoginButton")).click();
        Thread.sleep(3000);

        String actualMessage = driver.findElement(By.xpath("//*[contains(text(),\"Username\")]")).getText();
        Assert.assertEquals(actualMessage,"Username or password is incorrect");

        //Print message display on screen
        System.out.println("Message displayed on the screen for random username/password is : " + actualMessage);

    }

    @AfterTest
    public void testBrowserClose() {
        driver.quit();
    }
}
