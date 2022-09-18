package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class SeleniumSignUpTest {
    private final String YOUR_CHROME_DRIVER = "C:\\Users\\iosif\\Desktop\\chromedriver_win32\\chromedriver.exe";

    @Test
    void signup() {
        System.setProperty("webdriver.chrome.driver", YOUR_CHROME_DRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080");
        WebElement signUp = driver.findElement(By.id("mainSignUp"));
        signUp.click();
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        signUp = driver.findElement(By.id("signUp"));
        username.sendKeys("David");
        password.sendKeys("7");
        signUp.click();
    }

}
