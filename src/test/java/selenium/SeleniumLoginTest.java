package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class SeleniumLoginTest {

    private final String YOUR_CHROME_DRIVER = "C:\\Users\\iosif\\Desktop\\chromedriver_win32\\chromedriver.exe";

     @Test
     void login() {
        System.setProperty("webdriver.chrome.driver",YOUR_CHROME_DRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080");
        WebElement signIn = driver.findElement(By.id("logIn"));
        signIn.click();
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement login = driver.findElement(By.id("signIn"));
        username.sendKeys("iosif");
        password.sendKeys("ela");
        login.click();
    }

}
